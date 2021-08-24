package nl.siegmann.kingfisher.cms.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.siegmann.kingfisher.cms.domain.Catalog;
import nl.siegmann.kingfisher.cms.domain.CatalogVersion;
import nl.siegmann.kingfisher.cms.exception.RelatedObjectNotFoundException;
import nl.siegmann.kingfisher.cms.repository.CatalogVersionRepository;

@Service
public class CatalogVersionService {

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private CatalogVersionRepository catalogVersionRepository;

	public CatalogVersion save(nl.siegmann.kingfisher.api.domain.CatalogVersion source) throws RelatedObjectNotFoundException {
		Optional<Catalog> catalog = catalogService.findBySchemaKeyAndCatalogKey(source.getSchema(), source.getCatalog());
		if (!catalog.isPresent()) {
			throw new RelatedObjectNotFoundException();
		}

		CatalogVersion target = CatalogVersion.builder()
				.key(source.getKey())
				.catalog(catalog.get())
				.name(source.getName())
				.description(source.getDescription())
				.build();
		return catalogVersionRepository.save(target);
	}

	public Iterable<CatalogVersion> findAllBySchemaKeyAndCatalogKey(String schemaKey,
			String catalogKey) {
		return catalogVersionRepository.findAllBySchemaKeyAndCatalogKey(schemaKey, catalogKey);
	}

	public Optional<CatalogVersion> findBySchemaKeyAndCatalogKeyAndKey(String schemaKey, String catalogKey, String catalogVersionKey) {
		return catalogVersionRepository.findBySchemaKeyAndCatalogKeyAndKey(schemaKey, catalogKey, catalogVersionKey);
	}

	public CatalogVersion createCatalogVersion(String schemaKey, String catalogKey, String key) throws RelatedObjectNotFoundException {
		Optional<Catalog> catalog = catalogService.findBySchemaKeyAndCatalogKey(schemaKey, catalogKey);
		if (!catalog.isPresent()) {
			throw new RelatedObjectNotFoundException();
		}

		CatalogVersion catalogVersion = CatalogVersion.builder().catalog(catalog.get()).key(key).build();
		catalogVersionRepository.save(catalogVersion);
		return catalogVersion;
	}

	public Optional<CatalogVersion> deleteBySchemaKeyAndCatalogKeyAndKey(String schemaKey, String catalogKey,
			String key) {
		Optional<CatalogVersion> catalogVersion = catalogVersionRepository.findBySchemaKeyAndCatalogKeyAndKey(schemaKey, catalogKey, key);
		if (catalogVersion.isPresent()) {
			catalogVersionRepository.delete(catalogVersion.get());
		}
		return catalogVersion;
	}

	public Iterable<CatalogVersion> findByCatalogId(UUID catalogId) {
		return catalogVersionRepository.findByCatalogId(catalogId);
	}

	public Optional<CatalogVersion> findOneByCatalogIdAndKey(UUID catalogId, String catalogVersionKey) {
		return catalogVersionRepository.findOneByCatalogIdAndKey(catalogId, catalogVersionKey);
	}

	public CatalogVersion findOne(UUID catalogVersionId) {
		return catalogVersionRepository.findOne(catalogVersionId);
	}

}
