package nl.siegmann.kingfisher.api.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.api.domain.Field;
import nl.siegmann.kingfisher.api.domain.FieldValue;
import nl.siegmann.kingfisher.cms.domain.LocalizedStringField;
import nl.siegmann.kingfisher.cms.domain.StringField;
import nl.siegmann.kingfisher.cms.domain.TextField;

@Component
public class FieldConverter implements Function<nl.siegmann.kingfisher.cms.domain.Field, Field>{

	@Autowired
	private StringFieldValueConverter stringFieldValueConverter;
	
	@Autowired
	private LocalizedStringFieldValueConverter localizedStringFieldValueConverter;

	@Autowired
	private TextFieldValueConverter textFieldValueConverter;

	@Override
	public Field apply(nl.siegmann.kingfisher.cms.domain.Field source) {
		// @formatter:off
		return Field.builder()
				.key(source.getFieldDefinition().getKey())
				.type(source.getFieldDefinition().getFieldType().toString().toLowerCase())
//				.field(fieldDefinitionConverter.apply(source.getFieldDefinition()))
				.fieldValue(convertFieldValue(source))
		.build();
		// @formatter:on
	}
	
	FieldValue convertFieldValue(nl.siegmann.kingfisher.cms.domain.Field source) {
		FieldValue fieldValue = null;
		if (source instanceof StringField) {
			fieldValue = stringFieldValueConverter.apply(source);
		} else if (source instanceof TextField) {
			fieldValue = textFieldValueConverter.apply(source);
		} else if (source instanceof LocalizedStringField) {
			fieldValue = localizedStringFieldValueConverter.apply(source);
		}
		return fieldValue;
	}
}
