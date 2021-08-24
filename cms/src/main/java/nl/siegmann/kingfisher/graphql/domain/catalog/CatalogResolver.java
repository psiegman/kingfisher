package nl.siegmann.kingfisher.graphql.domain.catalog;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import graphql.schema.DataFetchingEnvironment;
import nl.siegmann.kingfisher.cms.service.CatalogVersionService;
import nl.siegmann.kingfisher.cms.service.SchemaService;
import nl.siegmann.kingfisher.graphql.domain.catalogversion.CatalogVersion;
import nl.siegmann.kingfisher.graphql.domain.catalogversion.GQLCatalogVersionConverter;
import nl.siegmann.kingfisher.graphql.domain.schema.GQLSchemaConverter;
import nl.siegmann.kingfisher.graphql.domain.schema.Schema;
import nl.siegmann.kingfisher.util.CollectionUtil;

@Component
public class CatalogResolver implements GraphQLResolver<Catalog> {

	@Autowired
	private CatalogVersionService catalogVersionService;
 
	@Autowired
	private GQLCatalogVersionConverter catalogVersionConverter;
	
	@Autowired
	private SchemaService schemaService;

	@Autowired
	private GQLSchemaConverter schemaConverter;
	
	@Transactional
    public List<CatalogVersion> getCatalogVersions(Catalog catalog) {
        return CollectionUtil.convertToList(catalogVersionService.findByCatalogId(catalog.getUUId()), catalogVersionConverter);
    }
	
    public Optional<CatalogVersion> getCatalogVersion(Catalog catalog, String catalogVersionKey) {
    	return CollectionUtil.convert(catalogVersionService.findOneByCatalogIdAndKey(catalog.getUUId(), catalogVersionKey), catalogVersionConverter);
    }

	public Schema getSchema(Catalog catalog, DataFetchingEnvironment dataFetchingEnvironment) {
		return schemaConverter.apply(schemaService.findOne(catalog.getSchemaId()));
	}
}