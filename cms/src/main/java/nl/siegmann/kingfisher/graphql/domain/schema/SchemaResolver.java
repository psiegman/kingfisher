package nl.siegmann.kingfisher.graphql.domain.schema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import nl.siegmann.kingfisher.cms.service.CatalogService;
import nl.siegmann.kingfisher.cms.service.ContentTypeService;
import nl.siegmann.kingfisher.graphql.domain.catalog.Catalog;
import nl.siegmann.kingfisher.graphql.domain.catalog.GQLCatalogConverter;
import nl.siegmann.kingfisher.graphql.domain.contenttype.ContentType;
import nl.siegmann.kingfisher.graphql.domain.contenttype.GQLContentTypeConverter;
import nl.siegmann.kingfisher.util.CollectionUtil;

@Component
public class SchemaResolver implements GraphQLResolver<Schema> {

	@Autowired
	private CatalogService catalogService;
 
	@Autowired
	private ContentTypeService contentTypeService;

	@Autowired
	private GQLCatalogConverter catalogConverter;
	
	@Autowired
	private GQLContentTypeConverter contentTypeConverter;
	
	@Transactional
    public List<Catalog> getCatalogs(Schema schema) {
        return CollectionUtil.convertToList(catalogService.findAllBySchemaId(schema.getUUId()), catalogConverter);
    }
	
	@Transactional
    public List<ContentType> getContentTypes(Schema schema) {
        return CollectionUtil.convertToList(contentTypeService.findAllBySchemaId(schema.getUUId()), contentTypeConverter);
    }

    public Optional<ContentType> getContentType(Schema schema, String contentTypeKey) {
        return CollectionUtil.convert(contentTypeService.findOneBySchemaIdAndKey(schema.getUUId(), contentTypeKey), contentTypeConverter);
    }

    public Optional<Catalog> getCatalog(Schema schema, String catalogKey) {
        return CollectionUtil.convert(catalogService.findOneBySchemaIdAndKey(UUID.fromString(schema.getId()), catalogKey), catalogConverter);
    }
}