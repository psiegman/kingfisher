package nl.siegmann.kingfisher.graphql.domain.fielddefinition;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

@Component
public class FieldDefinitionResolver implements GraphQLResolver<FieldDefinition> {

//	@Autowired
//	private ContentItemService contentItemService;
// 
//	@Autowired
//	private GQLContentItemConverter contentItemConverter;
//
//	
//    public int getNrContentItems(CatalogVersion catalogVersion) {
//    	return contentItemService.countByCatalogVersion(catalogVersion.getSchemaKey(), catalogVersion.getCatalogKey(), catalogVersion.getKey());
//    }
//
//	@Transactional
//    public List<ContentItem> getContentItems(CatalogVersion catalogVersion) {
//        return CollectionUtil.convertToList(contentItemService.findBySchemaKeyAndCatalogKeyAndCatalogVersionKeyAndKey(catalogVersion.getSchemaKey(), catalogVersion.getCatalogKey(), catalogVersion.getKey()), contentItemConverter);
//    }
}