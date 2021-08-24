package nl.siegmann.kingfisher.graphql.domain.catalogversion;

import java.util.function.Function;

import org.springframework.stereotype.Component;

@Component
public class GQLCatalogVersionConverter implements Function<nl.siegmann.kingfisher.cms.domain.CatalogVersion, CatalogVersion>{

	@Override
	public CatalogVersion apply(nl.siegmann.kingfisher.cms.domain.CatalogVersion source) {
		// @formatter:off
		return CatalogVersion.builder()
				.key(source.getKey())
				.id(source.getId())
				.catalogId(source.getCatalog().getId())
				.schemaId(source.getCatalog().getSchema().getId())
				.name(source.getName())
				.description(source.getDescription())
		.build();
		// @formatter:on
	}

}
