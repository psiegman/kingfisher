package nl.siegmann.kingfisher.graphql.domain.catalogversion;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import nl.siegmann.kingfisher.cms.exception.RelatedObjectNotFoundException;
import nl.siegmann.kingfisher.cms.service.CatalogVersionService;

@Component
public class CatalogVersionMutationResolver implements GraphQLMutationResolver {

	@Autowired
	private CatalogVersionService catalogVersionService;

	@Autowired
	private GQLCatalogVersionConverter catalogVersionConverter;

	@Transactional
	public CreateCatalogVersionPayload createCatalogVersion(CreateCatalogVersionInput catalogVersionInput) {
		nl.siegmann.kingfisher.cms.domain.CatalogVersion cmsCatalogVersion = null;
		try {
			cmsCatalogVersion = catalogVersionService.createCatalogVersion(catalogVersionInput.getSchemaKey(), catalogVersionInput.getCatalogKey(), catalogVersionInput.getKey());
			cmsCatalogVersion.setName(catalogVersionInput.getName());
			cmsCatalogVersion.setDescription(catalogVersionInput.getDescription());
		} catch (RelatedObjectNotFoundException e) {
		}
		return CreateCatalogVersionPayload.builder().catalogVersion(catalogVersionConverter.apply(cmsCatalogVersion)).build();
	}
	
	@Transactional
	public UpdateCatalogVersionPayload updateCatalogVersion(UpdateCatalogVersionInput updateCatalogVersionInput) {
		Optional<nl.siegmann.kingfisher.cms.domain.CatalogVersion> cmsCatalogVersion = catalogVersionService.findBySchemaKeyAndCatalogKeyAndKey(updateCatalogVersionInput.getSchemaKey(), updateCatalogVersionInput.getCatalogKey(), updateCatalogVersionInput.getKey());
		if (! cmsCatalogVersion.isPresent()) {
			return null;
		}
		cmsCatalogVersion.get().setName(updateCatalogVersionInput.getName());
		cmsCatalogVersion.get().setDescription(updateCatalogVersionInput.getDescription());
		return UpdateCatalogVersionPayload.builder().catalogVersion(catalogVersionConverter.apply(cmsCatalogVersion.get())).build();
	}

	@Transactional
	public DeleteCatalogVersionPayload deleteCatalogVersion(DeleteCatalogVersionInput catalogVersionInput) {
		Optional<nl.siegmann.kingfisher.cms.domain.CatalogVersion> cmsCatalogVersion = catalogVersionService.deleteBySchemaKeyAndCatalogKeyAndKey(catalogVersionInput.getSchemaKey(), catalogVersionInput.getCatalogKey(), catalogVersionInput.getKey());
		if (! cmsCatalogVersion.isPresent()) {
			return DeleteCatalogVersionPayload.builder().build();
		}
		return DeleteCatalogVersionPayload.builder().catalogVersion(catalogVersionConverter.apply(cmsCatalogVersion.get())).build();
	}

}
