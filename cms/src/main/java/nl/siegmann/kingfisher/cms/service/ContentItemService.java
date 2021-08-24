package nl.siegmann.kingfisher.cms.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.siegmann.kingfisher.api.domain.LocalizedStringFieldValue;
import nl.siegmann.kingfisher.api.domain.StringFieldValue;
import nl.siegmann.kingfisher.api.domain.TextFieldValue;
import nl.siegmann.kingfisher.api.json.ContentItemDeserializer;
import nl.siegmann.kingfisher.cms.domain.CatalogVersion;
import nl.siegmann.kingfisher.cms.domain.ContentItem;
import nl.siegmann.kingfisher.cms.domain.ContentType;
import nl.siegmann.kingfisher.cms.domain.Field;
import nl.siegmann.kingfisher.cms.domain.FieldDefinition;
import nl.siegmann.kingfisher.cms.domain.FieldType;
import nl.siegmann.kingfisher.cms.domain.LocalizedStringField;
import nl.siegmann.kingfisher.cms.domain.StringField;
import nl.siegmann.kingfisher.cms.domain.TextField;
import nl.siegmann.kingfisher.cms.exception.RelatedObjectNotFoundException;
import nl.siegmann.kingfisher.cms.repository.ContentItemRepository;
import nl.siegmann.kingfisher.cms.repository.ContentTypeRepository;

@Service
public class ContentItemService {

	@Autowired
	private CatalogVersionService catalogVersionService;

	@Autowired
	private ContentItemRepository contentItemRepository;

	@Autowired
	private ContentTypeRepository contentTypeRepository;

	@Autowired
	private ContentItemDeserializer contentItemDeserializer;

	public ContentItemDeserializer getJsonDeserializer() {
		return contentItemDeserializer;
	}

	public ContentItem save(nl.siegmann.kingfisher.api.domain.ContentItem source) throws RelatedObjectNotFoundException {
		Optional<CatalogVersion> catalogVersion = catalogVersionService.findBySchemaKeyAndCatalogKeyAndKey(
				source.getSchema(), source.getCatalog(), source.getCatalogVersion());
		if (!catalogVersion.isPresent()) {
			throw new RelatedObjectNotFoundException();
		}

		Optional<ContentType> contentType = contentTypeRepository.findBySchemaKeyAndKey(source.getSchema(),
				source.getContentType());
		if (!contentType.isPresent()) {
			throw new RelatedObjectNotFoundException();
		}

		Map<String, FieldDefinition> fieldDefinitions = contentType.get().getFieldDefinitions().stream()
				.collect(Collectors.toMap(FieldDefinition::getKey, Function.identity()));

		// @formatter:off
		ContentItem target = ContentItem.builder()
				.key(source.getKey())
				.catalogVersion(catalogVersion.get())
				.contentType(contentType.get())
				.name(source.getName())
				.description(source.getDescription())
				.build();
		// @formatter:on

		// @formatter:off
		List<Field> fields = source
					.getFields()
					.entrySet()
					.stream()
					.map(fieldEntry ->  convertField(fieldDefinitions, fieldEntry))
					.map(field -> {field.setContentItem(target);return field;})
					.collect(Collectors.toList());
		// @formatter:on

		target.setFields(fields);
		return contentItemRepository.save(target);
	}

	Field convertField(Map<String, FieldDefinition> fieldDefinitions,
			Entry<String, nl.siegmann.kingfisher.api.domain.Field> fieldEntry) {
		Field field = null;
		FieldDefinition fieldDefinition = fieldDefinitions.get(fieldEntry.getKey());
		if (fieldDefinition.getFieldType() == FieldType.STRING) {
			
			StringFieldValue stringFieldValue = (StringFieldValue) fieldEntry.getValue().getFieldValue();

			// @formatter:off
			field = StringField.builder()
					.value(stringFieldValue.getContent())
					.build();
			// @formatter:on
		} else if (fieldDefinition.getFieldType() == FieldType.L_STRING) {
			
			LocalizedStringFieldValue lStringFieldValue = (LocalizedStringFieldValue) fieldEntry.getValue().getFieldValue();
			
			// @formatter:off
			field = LocalizedStringField.builder()
					.build();
			// @formatter:on
			((LocalizedStringField) field).getValues().putAll(lStringFieldValue.getContents());
		} else if (fieldDefinition.getFieldType() == FieldType.TEXT) {
			
			TextFieldValue textFieldValue = (TextFieldValue) fieldEntry.getValue().getFieldValue();
			
			// @formatter:off
			field = TextField.builder()
					.mimeType(textFieldValue.getMimeType())
					.value(textFieldValue.getContent())
					.build();
			// @formatter:on
		}
		field.setFieldDefinition(fieldDefinition);
		return field;
	}
	
	public Optional<ContentItem> findOneBySchemaKeyAndCatalogKeyAndCatalogVersionKeyAndKey(String schemaKey,String catalogKey, String catalogVersionKey, String contentItemKey) {
		return contentItemRepository.findBySchemaKeyAndCatalogKeyAndCatalogVersionKeyAndKey(schemaKey, catalogKey,
				catalogVersionKey, contentItemKey);
	}


	public Iterable<ContentItem> findBySchemaKeyAndCatalogKeyAndCatalogVersionKeyAndKey(String schemaKey,String catalogKey, String catalogVersionKey) {
		return contentItemRepository.findBySchemaKeyAndCatalogKeyAndCatalogVersionKey(schemaKey, catalogKey,
				catalogVersionKey);
	}

	public Optional<ContentItem> findBySchemaKeyAndCatalogKeyAndCatalogVersionKeyAndKey(String schemaKey,
			String catalogKey, String catalogVersionKey, String contentItemKey) {
		return contentItemRepository.findBySchemaKeyAndCatalogKeyAndCatalogVersionKeyAndKey(schemaKey, catalogKey,
				catalogVersionKey, contentItemKey);
	}

	public int countByCatalogVersion(CatalogVersion catalogVersion) {
		return contentItemRepository.countByCatalogVersion(catalogVersion);
	}

	public int countByCatalogVersion(String schemaKey, String catalogKey, String catalogVersionKey) {
		return contentItemRepository.countBySchemaKeyAndCatalogKeyAndCatalogVersionKey(schemaKey, catalogKey, catalogVersionKey);
	}

	public int countByCatalogVersionId(UUID catalogVersionId) {
		return contentItemRepository.countByCatalogVersionId(catalogVersionId);
	}

	public Iterable<ContentItem> findByCatalogVersionId(UUID catalogVersionId) {
		return contentItemRepository.findByCatalogVersionId(catalogVersionId);
	}

	public Optional<ContentItem> findOneByCatalogVersionIdAndKey(UUID catalogVersionId, String contentItemKey) {
		return contentItemRepository.findOneByCatalogVersionIdAndKey(catalogVersionId, contentItemKey);
	}

	public ContentItem save(ContentItem contentItem) {
		return contentItemRepository.save(contentItem);
	}
}
