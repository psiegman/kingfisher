package nl.siegmann.kingfisher.graphql.domain.contenttype;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;

import graphql.schema.DataFetchingEnvironment;
import nl.siegmann.kingfisher.cms.service.SchemaService;
import nl.siegmann.kingfisher.graphql.domain.schema.GQLSchemaConverter;
import nl.siegmann.kingfisher.graphql.domain.schema.Schema;

@Component
public class ContentTypeResolver implements GraphQLResolver<ContentType> {

	@Autowired
	private SchemaService schemaService;
	
	@Autowired
	private GQLSchemaConverter schemaConverter;

	public Schema getSchema(ContentType contentType, DataFetchingEnvironment dataFetchingEnvironment) {
		return schemaConverter.apply(schemaService.findOne(contentType.getSchemaId()));
	}
}