package nl.siegmann.kingfisher.api.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.api.domain.CatalogVersion;
import nl.siegmann.kingfisher.cms.service.ContentItemService;

@Component
public class CatalogVersionConverter implements Function<nl.siegmann.kingfisher.cms.domain.CatalogVersion, CatalogVersion>{

	@Autowired
	private ContentItemService contentItemService;
	
	@Override
	public CatalogVersion apply(nl.siegmann.kingfisher.cms.domain.CatalogVersion source) {
		// @formatter:off
		return CatalogVersion.builder()
				.key(source.getKey())
				.name(source.getName())
				.schema(source.getCatalog().getSchema().getKey())
				.catalog(source.getCatalog().getKey())
				.nrContentItems(contentItemService.countByCatalogVersion(source))
				.description(source.getDescription())
		.build();
		// @formatter:on
	}

}
