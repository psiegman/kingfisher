package nl.siegmann.kingfisher.graphql.domain.catalog;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import nl.siegmann.kingfisher.cms.exception.RelatedObjectNotFoundException;
import nl.siegmann.kingfisher.cms.service.CatalogService;

@Component
public class CatalogMutationResolver implements GraphQLMutationResolver{

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private GQLCatalogConverter catalogConverter;

	@Transactional
	public CreateCatalogPayload createCatalog(CreateCatalogInput catalogInput) {
		nl.siegmann.kingfisher.cms.domain.Catalog cmsCatalog = null;
		try {
			cmsCatalog = catalogService.createCatalog(catalogInput.getSchemaKey(), catalogInput.getKey());
			cmsCatalog.setName(catalogInput.getName());
			cmsCatalog.setDescription(catalogInput.getDescription());
		} catch (RelatedObjectNotFoundException e) {
		}
		return CreateCatalogPayload.builder().catalog(catalogConverter.apply(cmsCatalog)).build();
	}
	
	@Transactional
	public UpdateCatalogPayload updateCatalog(UpdateCatalogInput catalog) {
		Optional<nl.siegmann.kingfisher.cms.domain.Catalog> cmsCatalog = catalogService.findBySchemaKeyAndCatalogKey(catalog.getSchemaKey(), catalog.getKey());
		if (! cmsCatalog.isPresent()) {
			return null;
		}
		cmsCatalog.get().setName(catalog.getName());
		cmsCatalog.get().setDescription(catalog.getDescription());
		return UpdateCatalogPayload.builder().catalog(catalogConverter.apply(cmsCatalog.get())).build();
	}

	@Transactional
	public DeleteCatalogPayload deleteCatalog(DeleteCatalogInput catalogInput) {
		Optional<nl.siegmann.kingfisher.cms.domain.Catalog> cmsCatalog = catalogService.deleteBySchemaKeyAndKey(catalogInput.getSchemaKey(), catalogInput.getKey());
		if (! cmsCatalog.isPresent()) {
			return DeleteCatalogPayload.builder().build();
		}
		return DeleteCatalogPayload.builder().catalog(catalogConverter.apply(cmsCatalog.get())).build();
	}

}
