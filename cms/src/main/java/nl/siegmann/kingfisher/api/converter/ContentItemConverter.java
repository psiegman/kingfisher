package nl.siegmann.kingfisher.api.converter;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.api.domain.ContentItem;

@Component
public class ContentItemConverter implements Function<nl.siegmann.kingfisher.cms.domain.ContentItem, ContentItem>{

	@Autowired
	private FieldConverter fieldConverter;
	
	@Override
	public ContentItem apply(nl.siegmann.kingfisher.cms.domain.ContentItem dbContentItem) {
		// @formatter:off
		return ContentItem.builder()
			.schema(dbContentItem.getCatalogVersion().getCatalog().getSchema().getKey())
			.catalog(dbContentItem.getCatalogVersion().getCatalog().getKey())
			.catalogVersion(dbContentItem.getCatalogVersion().getKey())
			.key(dbContentItem.getKey())
			.name(dbContentItem.getName())
			.description(dbContentItem.getDescription())
			.contentType(dbContentItem.getContentType().getKey())
			.fields(dbContentItem.getFields().stream().map(fieldConverter).collect(Collectors.toMap((field -> field.getKey()), Function.identity())))
			.build();
		// @formatter:on
	}
	
}
