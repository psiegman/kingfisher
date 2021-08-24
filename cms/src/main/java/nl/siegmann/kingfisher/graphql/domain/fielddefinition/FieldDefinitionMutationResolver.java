package nl.siegmann.kingfisher.graphql.domain.fielddefinition;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import nl.siegmann.kingfisher.cms.exception.RelatedObjectNotFoundException;
import nl.siegmann.kingfisher.cms.service.ContentTypeService;

@Component
public class FieldDefinitionMutationResolver implements GraphQLMutationResolver {

	@Autowired
	private ContentTypeService contentTypeService;

	@Autowired
	private GQLFieldDefinitionConverter fieldDefinitionConverter;

	@Transactional
	public CreateFieldDefinitionPayload createFieldDefinition(CreateFieldDefinitionInput fieldDefinitionInput) {
		nl.siegmann.kingfisher.cms.domain.FieldDefinition cmsFieldDefinition = null;
		try {
			cmsFieldDefinition = contentTypeService.createFieldDefinition(fieldDefinitionInput.getSchemaKey(), fieldDefinitionInput.getContentTypeKey(), fieldDefinitionInput.getKey());
			cmsFieldDefinition.setName(fieldDefinitionInput.getName());
			cmsFieldDefinition.setDescription(fieldDefinitionInput.getDescription());
		} catch (RelatedObjectNotFoundException e) {
		}
		return CreateFieldDefinitionPayload.builder().fieldDefinition(fieldDefinitionConverter.apply(cmsFieldDefinition)).build();
	}
	
	@Transactional
	public UpdateFieldDefinitionPayload updateFieldDefinition(UpdateFieldDefinitionInput updateFieldDefinitionInput) {
		Optional<nl.siegmann.kingfisher.cms.domain.FieldDefinition> cmsFieldDefinition = contentTypeService.findBySchemaKeyAndCatalogKeyAndKey(updateFieldDefinitionInput.getSchemaKey(), updateFieldDefinitionInput.getContentTypeKey(), updateFieldDefinitionInput.getKey());
		if (! cmsFieldDefinition.isPresent()) {
			return UpdateFieldDefinitionPayload.builder().build();
		}
		cmsFieldDefinition.get().setName(updateFieldDefinitionInput.getName());
		cmsFieldDefinition.get().setDescription(updateFieldDefinitionInput.getDescription());
		return UpdateFieldDefinitionPayload.builder().fieldDefinition(fieldDefinitionConverter.apply(cmsFieldDefinition.get())).build();
	}

	@Transactional
	public DeleteFieldDefinitionPayload deleteFieldDefinition(DeleteFieldDefinitionInput fieldDefinitionInput) {
		Optional<nl.siegmann.kingfisher.cms.domain.FieldDefinition> cmsFieldDefinition = contentTypeService.deleteBySchemaKeyAndCatalogKeyAndKey(fieldDefinitionInput.getSchemaKey(), fieldDefinitionInput.getContentTypeKey(), fieldDefinitionInput.getKey());
		if (! cmsFieldDefinition.isPresent()) {
			return DeleteFieldDefinitionPayload.builder().build();
		}
		return DeleteFieldDefinitionPayload.builder().fieldDefinition(fieldDefinitionConverter.apply(cmsFieldDefinition.get())).build();
	}

}
