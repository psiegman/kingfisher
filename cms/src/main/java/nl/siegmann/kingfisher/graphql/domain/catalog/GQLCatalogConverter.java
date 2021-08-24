package nl.siegmann.kingfisher.graphql.domain.catalog;

import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class GQLCatalogConverter implements Function<nl.siegmann.kingfisher.cms.domain.Catalog, Catalog>{

	@Override
	public Catalog apply(nl.siegmann.kingfisher.cms.domain.Catalog source) {
		// @formatter:off
		return Catalog.builder()
				.id(source.getId())
				.schemaId(source.getSchema().getId())
				.key(source.getKey())
				.name(source.getName())
				.description(source.getDescription())
		.build();
		// @formatter:on
	}

}
