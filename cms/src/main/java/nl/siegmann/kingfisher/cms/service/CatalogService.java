package nl.siegmann.kingfisher.cms.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.siegmann.kingfisher.cms.domain.Catalog;
import nl.siegmann.kingfisher.cms.domain.Schema;
import nl.siegmann.kingfisher.cms.exception.RelatedObjectNotFoundException;
import nl.siegmann.kingfisher.cms.repository.CatalogRepository;
import nl.siegmann.kingfisher.cms.repository.SchemaRepository;

@Service
public class CatalogService {

	@Autowired
	private SchemaRepository schemaRepository;

	@Autowired
	private CatalogRepository catalogRepository;

	public Catalog save(nl.siegmann.kingfisher.api.domain.Catalog source) throws RelatedObjectNotFoundException {
		Optional<Schema> schema = schemaRepository.findByKey(source.getSchema());
		if (!schema.isPresent()) {
			throw new RelatedObjectNotFoundException();
		}

//		List<CatalogVersion> catalogVersions = SchemaService.getRelated(source.getCatalogVersions(), new Foo(source.getSchema(), source.getKey()));

		// @formatter:off
		Catalog target = Catalog.builder()
				.key(source.getKey())
				.schema(schema.get())
				.name(source.getName())
				.description(source.getDescription())
				.build();
		// @formatter:on
		return catalogRepository.save(target);
	}

	public Optional<Catalog> findBySchemaKeyAndCatalogKey(String schemaKey, String catalogKey) {
		return catalogRepository.findBySchemaKeyAndKey(schemaKey, catalogKey);
	}

	public Optional<Catalog> findBySchemaKeyAndKey(String schemaKey, String catalogKey) {
		return catalogRepository.findBySchemaKeyAndKey(schemaKey, catalogKey);
	}

	public Iterable<Catalog> findAllBySchemaKey(String schemaKey) {
		return catalogRepository.findAllBySchemaKey(schemaKey);
	}

	public Optional<Catalog> deleteBySchemaKeyAndKey(String schemaKey, String key) {
		Optional<Catalog> catalog = catalogRepository.findBySchemaKeyAndKey(schemaKey, key);
		if (catalog.isPresent()) {
			catalogRepository.delete(catalog.get());
		}
		return catalog;
	}

	public Catalog createCatalog(String schemaKey, String key) throws RelatedObjectNotFoundException {
		Optional<Schema> schema = schemaRepository.findByKey(schemaKey);
		if (!schema.isPresent()) {
			throw new RelatedObjectNotFoundException();
		}
		Catalog catalog = Catalog.builder().key(key).schema(schema.get()).build();
		catalogRepository.save(catalog);
		return catalog;
	}

	public Catalog findOne(UUID catalogId) {
		return catalogRepository.findOne(catalogId);
	}

	public Iterable<Catalog> findAllBySchemaId(UUID schemaId) {
		return catalogRepository.findAllBySchemaId(schemaId);
	}

	public Optional<Catalog> findOneBySchemaIdAndKey(UUID schemaId, String catalogKey) {
		return catalogRepository.findOneBySchemaIdAndKey(schemaId, catalogKey);
	}

}
