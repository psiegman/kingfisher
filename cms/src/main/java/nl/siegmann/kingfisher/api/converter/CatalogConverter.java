package nl.siegmann.kingfisher.api.converter;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.api.domain.Catalog;
import nl.siegmann.kingfisher.cms.domain.CatalogVersion;
import nl.siegmann.kingfisher.util.CollectionUtil;

@Component
public class CatalogConverter implements Function<nl.siegmann.kingfisher.cms.domain.Catalog, Catalog>{

	@Override
	public Catalog apply(nl.siegmann.kingfisher.cms.domain.Catalog source) {
		// @formatter:off
		return Catalog.builder()
				.key(source.getKey())
				.name(source.getName())
				.schema(source.getSchema().getKey())
				.description(source.getDescription())
				.catalogVersionKeys(CollectionUtil.toStream(source.getCatalogVersions()).map(CatalogVersion::getKey).collect(Collectors.toList()))
		.build();
		// @formatter:on
	}

}
