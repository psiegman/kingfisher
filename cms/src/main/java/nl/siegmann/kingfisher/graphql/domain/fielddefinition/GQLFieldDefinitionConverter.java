package nl.siegmann.kingfisher.graphql.domain.fielddefinition;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.graphql.converter.GQLFieldTypeConverter;

@Component
public class GQLFieldDefinitionConverter implements Function<nl.siegmann.kingfisher.cms.domain.FieldDefinition, FieldDefinition>{

	@Autowired
	private GQLFieldTypeConverter fieldTypeConverter;
	
	@Override
	public FieldDefinition apply(nl.siegmann.kingfisher.cms.domain.FieldDefinition source) {
		// @formatter:off
		return FieldDefinition.builder()
				.key(source.getKey())
				.contentTypeKey(source.getContentType().getKey())
				.schemaKey(source.getContentType().getSchema().getKey())
				.name(source.getName())
				.description(source.getDescription())
				.fieldType(fieldTypeConverter.apply(source.getFieldType()))
		.build();
		// @formatter:on
	}

}
