package nl.siegmann.kingfisher.api.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.api.domain.LocalizedStringFieldValue;
import nl.siegmann.kingfisher.cms.domain.LocalizedStringField;

@Component
public class LocalizedStringFieldValueConverter implements Function<nl.siegmann.kingfisher.cms.domain.Field, LocalizedStringFieldValue> {

	@Override
	public LocalizedStringFieldValue apply(nl.siegmann.kingfisher.cms.domain.Field source) {
		if (!(source instanceof LocalizedStringField)) {
			return null;
		}
		LocalizedStringField stringField = (LocalizedStringField) source;
		LocalizedStringFieldValue target = LocalizedStringFieldValue.builder().build();
		target.getContents().putAll(stringField.getValues());
		return target;
	}

}
