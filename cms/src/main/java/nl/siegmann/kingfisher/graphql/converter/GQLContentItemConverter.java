package nl.siegmann.kingfisher.graphql.converter;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.graphql.domain.contentitem.ContentItem;

@Component
public class GQLContentItemConverter implements Function<nl.siegmann.kingfisher.cms.domain.ContentItem, ContentItem>{

	@Autowired
	private GQLFieldConverter fieldConverter;
	
	@Override
	public ContentItem apply(nl.siegmann.kingfisher.cms.domain.ContentItem dbContentItem) {
		// @formatter:off
		return ContentItem.builder()
			.id(dbContentItem.getId())
			.key(dbContentItem.getKey())
			.name(dbContentItem.getName())
			.description(dbContentItem.getDescription())
			.catalogVersionId(dbContentItem.getCatalogVersionId())
			.contentTypeId(dbContentItem.getContentTypeId())
			.fields(dbContentItem.getFields().stream().map(fieldConverter).collect(Collectors.toList()))
			.build();
		// @formatter:on
	}
	
}
