package nl.siegmann.kingfisher.graphql.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.cms.domain.LocalizedStringField;
import nl.siegmann.kingfisher.cms.domain.StringField;
import nl.siegmann.kingfisher.cms.domain.TextField;
import nl.siegmann.kingfisher.graphql.domain.contentitem.Field;
import nl.siegmann.kingfisher.graphql.domain.contentitem.FieldValue;
import nl.siegmann.kingfisher.graphql.domain.fielddefinition.GQLFieldDefinitionConverter;

@Component
public class GQLFieldConverter implements Function<nl.siegmann.kingfisher.cms.domain.Field, Field> {

	@Autowired
	private GQLStringFieldValueConverter stringFieldValueConverter;

	@Autowired
	private GQLLocalizedStringFieldValueConverter localizedStringFieldValueConverter;

	@Autowired
	private GQLTextFieldValueConverter textFieldValueConverter;

	@Autowired
	private GQLFieldDefinitionConverter fieldDefinitionConverter;

	@Override
	public Field apply(nl.siegmann.kingfisher.cms.domain.Field source) {
		// @formatter:off
		return Field.builder()
				.key(source.getFieldDefinition().getKey())
				.fieldDefinition(fieldDefinitionConverter.apply(source.getFieldDefinition()))
				.value(convertFieldValue(source))
		.build();
		// @formatter:on
	}

	FieldValue convertFieldValue(nl.siegmann.kingfisher.cms.domain.Field source) {
		FieldValue fieldValue = null;
		if (source instanceof StringField) {
			fieldValue = stringFieldValueConverter.apply(source);
		} else if (source instanceof LocalizedStringField) {
			fieldValue = localizedStringFieldValueConverter.apply(source);
		} else if (source instanceof TextField) {
			fieldValue = textFieldValueConverter.apply(source);
		}
		return fieldValue;
	}

}
