package nl.siegmann.kingfisher.graphql.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.cms.domain.FieldType;

@Component
public class GQLFieldTypeConverter implements Function<FieldType, nl.siegmann.kingfisher.graphql.domain.contenttype.FieldType> {

	@Override
	public nl.siegmann.kingfisher.graphql.domain.contenttype.FieldType apply(FieldType source) {
		switch(source) {
			case STRING:return nl.siegmann.kingfisher.graphql.domain.contenttype.FieldType.STRING;
			case TEXT:return nl.siegmann.kingfisher.graphql.domain.contenttype.FieldType.TEXT;
			case L_STRING:return nl.siegmann.kingfisher.graphql.domain.contenttype.FieldType.ISTRING;
		}
		return nl.siegmann.kingfisher.graphql.domain.contenttype.FieldType.STRING;
	}

}
