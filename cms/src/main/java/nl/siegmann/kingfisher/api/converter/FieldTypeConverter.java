package nl.siegmann.kingfisher.api.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.cms.domain.FieldType;

@Component
public class FieldTypeConverter implements Function<FieldType, nl.siegmann.kingfisher.api.domain.FieldType> {

	@Override
	public nl.siegmann.kingfisher.api.domain.FieldType apply(FieldType source) {
		switch(source) {
			case STRING:return nl.siegmann.kingfisher.api.domain.FieldType.STRING;
			case TEXT:return nl.siegmann.kingfisher.api.domain.FieldType.TEXT;
			case L_STRING:return nl.siegmann.kingfisher.api.domain.FieldType.L_STRING;
		}
		return nl.siegmann.kingfisher.api.domain.FieldType.STRING;
	}

}
