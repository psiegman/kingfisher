package nl.siegmann.kingfisher.api.converter;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.api.domain.Schema;
import nl.siegmann.kingfisher.cms.domain.Catalog;
import nl.siegmann.kingfisher.util.CollectionUtil;

@Component
public class SchemaConverter implements Function<nl.siegmann.kingfisher.cms.domain.Schema, Schema> {

	public Schema apply(nl.siegmann.kingfisher.cms.domain.Schema dbSchema) {
		return Schema.builder()
				.key(dbSchema.getKey())
				.name(dbSchema.getName())
				.description(dbSchema.getDescription())
				.catalogKeys(CollectionUtil.toStream(dbSchema.getCatalogs()).map(Catalog::getKey).collect(Collectors.toList()))
		.build();
	}
}
