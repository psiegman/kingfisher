package nl.siegmann.kingfisher.api.converter;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.api.domain.ContentType;

@Component
public class ContentTypeConverter implements Function<nl.siegmann.kingfisher.cms.domain.ContentType, ContentType>{

	@Autowired
	private FieldDefinitionConverter fieldDefinitionConverter;
	
	@Override
	public ContentType apply(nl.siegmann.kingfisher.cms.domain.ContentType source) {
		// @formatter:off
		return ContentType.builder()
				.key(source.getKey())
				.schema(source.getSchema().getKey())
				.name(source.getName())
				.description(source.getDescription())
				.fieldDefinitions(source.getFieldDefinitions().stream().map(fieldDefinitionConverter).collect(Collectors.toList()))
		.build();
		// @formatter:on
	}

}
