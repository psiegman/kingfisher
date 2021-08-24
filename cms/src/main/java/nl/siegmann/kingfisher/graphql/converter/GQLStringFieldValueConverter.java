package nl.siegmann.kingfisher.graphql.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.cms.domain.StringField;
import nl.siegmann.kingfisher.graphql.domain.contentitem.StringFieldValue;

@Component
public class GQLStringFieldValueConverter implements Function<nl.siegmann.kingfisher.cms.domain.Field, StringFieldValue> {

	@Override
	public StringFieldValue apply(nl.siegmann.kingfisher.cms.domain.Field source) {
		if (!(source instanceof StringField)) {
			return null;
		}
		StringField stringField = (StringField) source;
		// @formatter:off
		return StringFieldValue.builder()
				.content(stringField.getValue())
		.build();
		// @formatter:on
	}

}
