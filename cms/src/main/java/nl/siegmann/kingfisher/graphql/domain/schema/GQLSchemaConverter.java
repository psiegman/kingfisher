package nl.siegmann.kingfisher.graphql.domain.schema;

import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class GQLSchemaConverter implements Function<nl.siegmann.kingfisher.cms.domain.Schema, Schema> {

	public Schema apply(nl.siegmann.kingfisher.cms.domain.Schema dbSchema) {
		return Schema.builder()
				.id(dbSchema.getId())
				.key(dbSchema.getKey())
				.name(dbSchema.getName())
				.description(dbSchema.getDescription())
		.build();
	}
}
