package nl.siegmann.kingfisher.graphql.domain.schema;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import nl.siegmann.kingfisher.cms.domain.Schema;
import nl.siegmann.kingfisher.cms.service.SchemaService;

@Component
public class SchemaMutationResolver implements GraphQLMutationResolver {

	@Autowired
	private SchemaService schemaService;

	@Autowired
	private GQLSchemaConverter schemaConverter;

	@Transactional
	public CreateSchemaPayload createSchema(CreateSchemaInput schemaInput) {
		Schema schema = schemaService.createSchema(schemaInput.getKey());
		schema.setName(schemaInput.getName());
		schema.setDescription(schemaInput.getDescription());
		return CreateSchemaPayload.builder().schema(schemaConverter.apply(schema)).build();
	}
	
	@Transactional
	public UpdateSchemaPayload updateSchema(UpdateSchemaInput schema) {
		Optional<nl.siegmann.kingfisher.cms.domain.Schema> cmsSchema = schemaService.findSchemaByKey(schema.getKey());
		if (! cmsSchema.isPresent()) {
			return null;
		}
		cmsSchema.get().setName(schema.getName());
		cmsSchema.get().setDescription(schema.getDescription());
		return UpdateSchemaPayload.builder().schema(schemaConverter.apply(cmsSchema.get())).build();
	}

	@Transactional
	public DeleteSchemaPayload deleteSchema(DeleteSchemaInput schemaInput) {
		Optional<nl.siegmann.kingfisher.cms.domain.Schema> cmsSchema = schemaService.deleteSchemaByKey(schemaInput.getKey());
		if (! cmsSchema.isPresent()) {
			return DeleteSchemaPayload.builder().build();
		}
		return DeleteSchemaPayload.builder().schema(schemaConverter.apply(cmsSchema.get())).build();
	}

}
