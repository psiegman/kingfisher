package nl.siegmann.kingfisher.graphql.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.cms.domain.TextField;
import nl.siegmann.kingfisher.graphql.domain.contentitem.TextFieldValue;

@Component
public class GQLTextFieldValueConverter implements Function<nl.siegmann.kingfisher.cms.domain.Field, TextFieldValue> {

	@Override
	public TextFieldValue apply(nl.siegmann.kingfisher.cms.domain.Field source) {
		if (!(source instanceof TextField)) {
			return null;
		}
		TextField textField = (TextField) source;
		// @formatter:off
		return TextFieldValue.builder()
				.mimeType(textField.getMimeType())
				.content(textField.getValue())
		.build();
		// @formatter:on
	}

}
