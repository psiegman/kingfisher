package nl.siegmann.kingfisher.graphql.domain.contenttype;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import nl.siegmann.kingfisher.cms.exception.RelatedObjectNotFoundException;
import nl.siegmann.kingfisher.cms.service.ContentTypeService;

@Component
public class ContentTypeMutationResolver implements GraphQLMutationResolver {

	@Autowired
	private ContentTypeService contentTypeService;

	@Autowired
	private GQLContentTypeConverter contentTypeConverter;

	@Transactional
	public CreateContentTypePayload createContentType(CreateContentTypeInput contentTypeInput) {
		nl.siegmann.kingfisher.cms.domain.ContentType cmsContentType = null;
		try {
			cmsContentType = contentTypeService.createContentType(contentTypeInput.getSchemaKey(),
					contentTypeInput.getKey());
			cmsContentType.setName(contentTypeInput.getName());
			cmsContentType.setDescription(contentTypeInput.getDescription());
		} catch (RelatedObjectNotFoundException e) {
		}
		return CreateContentTypePayload.builder().contentType(contentTypeConverter.apply(cmsContentType)).build();
	}

	@Transactional
	public UpdateContentTypePayload updateContentType(UpdateContentTypeInput contentType) {
		Optional<nl.siegmann.kingfisher.cms.domain.ContentType> cmsContentType = contentTypeService
				.findBySchemaKeyAndKey(contentType.getSchemaKey(), contentType.getKey());
		if (!cmsContentType.isPresent()) {
			return null;
		}
		cmsContentType.get().setName(contentType.getName());
		cmsContentType.get().setDescription(contentType.getDescription());
		return UpdateContentTypePayload.builder().contentType(contentTypeConverter.apply(cmsContentType.get())).build();
	}

	@Transactional
	public DeleteContentTypePayload deleteContentType(DeleteContentTypeInput contentTypeInput) {
		Optional<nl.siegmann.kingfisher.cms.domain.ContentType> cmsContentType = contentTypeService
				.deleteBySchemaKeyAndKey(contentTypeInput.getSchemaKey(), contentTypeInput.getKey());
		if (!cmsContentType.isPresent()) {
			return DeleteContentTypePayload.builder().build();
		}
		return DeleteContentTypePayload.builder().contentType(contentTypeConverter.apply(cmsContentType.get())).build();
	}
}
