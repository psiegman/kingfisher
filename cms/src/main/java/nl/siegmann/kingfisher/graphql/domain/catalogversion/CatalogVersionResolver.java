package nl.siegmann.kingfisher.graphql.domain.catalogversion;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import nl.siegmann.kingfisher.cms.service.CatalogService;
import nl.siegmann.kingfisher.cms.service.ContentItemService;
import nl.siegmann.kingfisher.graphql.converter.GQLContentItemConverter;
import nl.siegmann.kingfisher.graphql.domain.catalog.Catalog;
import nl.siegmann.kingfisher.graphql.domain.catalog.GQLCatalogConverter;
import nl.siegmann.kingfisher.graphql.domain.contentitem.ContentItem;
import nl.siegmann.kingfisher.util.CollectionUtil;

@Component
public class CatalogVersionResolver implements GraphQLResolver<CatalogVersion> {

	@Autowired
	private ContentItemService contentItemService;
 
	@Autowired
	private GQLContentItemConverter contentItemConverter;

	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private GQLCatalogConverter catalogConverter;
	
    public int getNrContentItems(CatalogVersion catalogVersion) {
    	return contentItemService.countByCatalogVersionId(catalogVersion.getUUId());
    }

	@Transactional
    public List<ContentItem> getContentItems(CatalogVersion catalogVersion) {
        return CollectionUtil.convertToList(contentItemService.findByCatalogVersionId(catalogVersion.getUUId()), contentItemConverter);
    }
	
	@Transactional
    public Optional<ContentItem> getContentItem(CatalogVersion catalogVersion, String contentItemKey) {
        return CollectionUtil.convert(contentItemService.findOneByCatalogVersionIdAndKey(catalogVersion.getUUId(), contentItemKey), contentItemConverter);
    }

    public Catalog getCatalog(CatalogVersion catalogVersion) {
		return catalogConverter.apply(catalogService.findOne(catalogVersion.getCatalogId()));
	}
}