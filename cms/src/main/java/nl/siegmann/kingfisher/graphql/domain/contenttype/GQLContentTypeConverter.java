package nl.siegmann.kingfisher.graphql.domain.contenttype;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.graphql.domain.fielddefinition.GQLFieldDefinitionConverter;

@Component
public class GQLContentTypeConverter implements Function<nl.siegmann.kingfisher.cms.domain.ContentType, ContentType>{

	@Autowired
	private GQLFieldDefinitionConverter fieldDefinitionConverter;
	
	@Override
	public ContentType apply(nl.siegmann.kingfisher.cms.domain.ContentType source) {
		// @formatter:off
		return ContentType.builder()
				.id(source.getId())
				.key(source.getKey())
				.schemaId(source.getSchema().getId())
				.name(source.getName())
				.description(source.getDescription())
				.fieldDefinitions(source.getFieldDefinitions().stream().map(fieldDefinitionConverter).collect(Collectors.toList()))
		.build();
		// @formatter:on
	}

}
