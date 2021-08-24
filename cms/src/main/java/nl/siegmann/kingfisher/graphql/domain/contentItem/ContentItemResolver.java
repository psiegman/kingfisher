package nl.siegmann.kingfisher.graphql.domain.contentitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import nl.siegmann.kingfisher.cms.service.CatalogVersionService;
import nl.siegmann.kingfisher.cms.service.ContentTypeService;
import nl.siegmann.kingfisher.graphql.domain.catalogversion.CatalogVersion;
import nl.siegmann.kingfisher.graphql.domain.catalogversion.GQLCatalogVersionConverter;
import nl.siegmann.kingfisher.graphql.domain.contenttype.ContentType;
import nl.siegmann.kingfisher.graphql.domain.contenttype.GQLContentTypeConverter;

@Component
public class ContentItemResolver implements GraphQLResolver<ContentItem> {

	@Autowired
	private ContentTypeService contentTypeService;

	@Autowired
	private GQLContentTypeConverter contentTypeConverter;

	@Autowired
	private CatalogVersionService catalogVersionService;

	@Autowired
	private GQLCatalogVersionConverter catalogVersionConverter;

	public ContentType getContentType(ContentItem contentItem) {
		return contentTypeConverter.apply(contentTypeService.findOne(contentItem.getContentTypeId()));
	}

	public CatalogVersion getCatalogVersion(ContentItem contentItem) {
		return catalogVersionConverter.apply(catalogVersionService.findOne(contentItem.getCatalogVersionId()));
	}
}