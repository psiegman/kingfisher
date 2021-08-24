package nl.siegmann.kingfisher.graphql.domain.cmscontent;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import nl.siegmann.kingfisher.cms.service.SchemaService;
import nl.siegmann.kingfisher.graphql.domain.CmsContent;
import nl.siegmann.kingfisher.graphql.domain.schema.GQLSchemaConverter;
import nl.siegmann.kingfisher.graphql.domain.schema.Schema;
import nl.siegmann.kingfisher.util.CollectionUtil;

@Component
public class CmsContentResolver implements GraphQLResolver<CmsContent> {

	@Autowired
	private SchemaService schemaService;

	@Autowired
	private GQLSchemaConverter schemaConverter;

	@Transactional
	public List<Schema> getSchemas(CmsContent cmsContent) {
		List<Schema> schemas = CollectionUtil.convertToList(schemaService.findAll(), schemaConverter);
		cmsContent.setSchemas(schemas);
		return schemas;
	}

	@Transactional
	public Optional<Schema> getSchema(CmsContent cmsContent, String schemaKey) {
		return CollectionUtil.convert(schemaService.findSchemaByKey(schemaKey), schemaConverter);
	}

}