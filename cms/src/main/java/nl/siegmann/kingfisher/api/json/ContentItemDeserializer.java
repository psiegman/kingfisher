package nl.siegmann.kingfisher.api.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import nl.siegmann.kingfisher.api.domain.ContentItem;
import nl.siegmann.kingfisher.api.domain.Field;
import nl.siegmann.kingfisher.api.domain.FieldValue;
import nl.siegmann.kingfisher.api.domain.LocalizedStringFieldValue;
import nl.siegmann.kingfisher.api.domain.StringFieldValue;
import nl.siegmann.kingfisher.api.domain.TextFieldValue;
import nl.siegmann.kingfisher.cms.domain.ContentType;
import nl.siegmann.kingfisher.cms.domain.FieldDefinition;
import nl.siegmann.kingfisher.cms.domain.FieldType;
import nl.siegmann.kingfisher.cms.service.ContentTypeService;

@Component
public class ContentItemDeserializer extends StdDeserializer<ContentItem> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6041400700644886467L;

	@Autowired
	private ContentTypeService contentTypeService;

	public ContentItemDeserializer() {
		this(null);
	}

	public ContentItemDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public ContentItem deserialize(JsonParser parser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		JsonNode contentItemNode = parser.getCodec().readTree(parser);
		// @formatter:off
 		ContentItem contentItem = ContentItem.builder()
			.schema(getFieldAsText(contentItemNode, "schema"))
			.catalog(getFieldAsText(contentItemNode, "catalog"))
			.catalogVersion(getFieldAsText(contentItemNode, "catalogVersion"))
			.key(getFieldAsText(contentItemNode, "key"))
			.name(getFieldAsText(contentItemNode, "name"))
			.description(getFieldAsText(contentItemNode, "description"))
			.contentType(getFieldAsText(contentItemNode, "contentType"))
			.build();
		// @formatter:on

		Optional<ContentType> contentType = contentTypeService.findBySchemaKeyAndKey(contentItem.getSchema(),
				contentItem.getContentType());
		if (!contentType.isPresent()) {
			throw new JsonMappingException(parser,
					"ContentType " + contentItem.getContentType() + " not found in schema " + contentItem.getSchema());
		}

		Map<String, Field> fields = deserializeFields(contentItemNode, contentType.get());
		contentItem.setFields(fields);
		return contentItem;
	}

	private String getFieldAsText(JsonNode jsonNode, String fieldName) {
		JsonNode fieldNode = jsonNode.get(fieldName);
		if (fieldNode == null) {
			return null;
		}
		return fieldNode.asText();
	}
	
	private Map<String, Field> deserializeFields(JsonNode contentItemNode, ContentType contentType) {
		Map<String, Field> fields = new HashMap<>();
		Map<String, FieldDefinition> fieldDefinitions = contentType.getFieldDefinitions().stream()
				.collect(Collectors.toMap(FieldDefinition::getKey, Function.identity()));
		for (Iterator<Entry<String, JsonNode>> fieldNodes = contentItemNode.get("fields").fields(); fieldNodes
				.hasNext();) {
			Entry<String, JsonNode> fieldData = fieldNodes.next();
			Field field = deserializeField(fieldDefinitions, fieldData);
			fields.put(field.getKey(), field);
		}
		return fields;
	}

	private Field deserializeField(Map<String, FieldDefinition> fieldDefinitions, Entry<String, JsonNode> fieldData) {
		FieldDefinition fieldDefinition = fieldDefinitions.get(fieldData.getKey());
		//@formatter:off
		return Field.builder()
				.key(fieldData.getKey())
				.type(fieldDefinition.getFieldType().toString().toLowerCase())
				.fieldValue(deserializeFieldValue(fieldDefinition, fieldData))
				.build();
		//@formatter:on
	}

	private FieldValue deserializeFieldValue(FieldDefinition fieldDefinition,
			Entry<String, JsonNode> fieldData) {
		FieldValue fieldValue = null;
		if (fieldDefinition.getFieldType() == FieldType.STRING) {
			//@formatter:off
			fieldValue = StringFieldValue.builder()
				.content(fieldData.getValue().path("value").path("content").asText())
				.build();
			//@formatter:on
		} else if (fieldDefinition.getFieldType() == FieldType.L_STRING) {
			LocalizedStringFieldValue lstringValue = LocalizedStringFieldValue.builder().build();
			fieldData.getValue().path("value").fields().forEachRemaining(localeValue -> 
			lstringValue.getContents().put(
				StringUtils.parseLocaleString(localeValue.getKey()),
				localeValue.getValue().asText()));
			fieldValue = lstringValue;
		} else if (fieldDefinition.getFieldType() == FieldType.TEXT) {
				//@formatter:off
				fieldValue = TextFieldValue.builder()
					.mimeType(fieldData.getValue().path("value").path("mimeType").asText())
					.content(fieldData.getValue().path("value").path("content").asText())
					.build();
				//@formatter:on
		}
		return fieldValue;
	}

}
