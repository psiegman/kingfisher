package nl.siegmann.kingfisher.api.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.api.domain.TextFieldValue;
import nl.siegmann.kingfisher.cms.domain.TextField;

@Component
public class TextFieldValueConverter implements Function<nl.siegmann.kingfisher.cms.domain.Field, TextFieldValue> {

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
