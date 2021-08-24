package nl.siegmann.kingfisher.api.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.api.domain.FieldDefinition;

@Component
public class FieldDefinitionConverter implements Function<nl.siegmann.kingfisher.cms.domain.FieldDefinition, FieldDefinition>{

	@Autowired
	private FieldTypeConverter fieldTypeConverter;
	
	@Override
	public FieldDefinition apply(nl.siegmann.kingfisher.cms.domain.FieldDefinition source) {
		// @formatter:off
		return FieldDefinition.builder()
				.key(source.getKey())
				.name(source.getName())
				.description(source.getDescription())
				.fieldType(fieldTypeConverter.apply(source.getFieldType()))
		.build();
		// @formatter:on
	}

}
