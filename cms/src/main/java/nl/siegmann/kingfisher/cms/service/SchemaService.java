package nl.siegmann.kingfisher.cms.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.siegmann.kingfisher.cms.domain.Schema;
import nl.siegmann.kingfisher.cms.exception.RelatedObjectNotFoundException;
import nl.siegmann.kingfisher.cms.repository.SchemaRepository;

@Service
public class SchemaService {

	@Autowired
	private SchemaRepository schemaRepository;
	
//	@Autowired
//	private CatalogRepository catalogRepository;

//	private class Foo implements Function<String, Optional<Catalog>> {
//		
//		private String schemaKey;
//		
//		
//		public Foo(String schemaKey) {
//			this.schemaKey = schemaKey;
//		}
//		
//		@Override
//		public Optional<Catalog> apply(String catalogKey) {
//			return catalogRepository.findBySchemaKeyAndKey(schemaKey, catalogKey);
//		}	
//	}
	
	
	public Schema findOne(UUID schemaId) {
		return schemaRepository.findOne(schemaId);
	}
	
	public Iterable<Schema> findAll() {
		return schemaRepository.findAll();
	}
	
	public Schema save(nl.siegmann.kingfisher.api.domain.Schema source) throws RelatedObjectNotFoundException {
//		List<Catalog> catalogs = getRelated(source.getCatalogKeys(), new Foo(source.getKey()));
		// @formatter:off
		Schema target = Schema.builder()
				.key(source.getKey())
				.name(source.getName())
				.description(source.getDescription())
//				.catalogs(catalogs)
		.build();
		// @formatter:on
		return schemaRepository.save(target);
	}

//	public static <T> List<T> getRelated(List<String> keys, Function<String, Optional<T>> keyToRelated) {
//		List<T> catalogs = null;
//		if (keys == null) {
//		} else if (keys.isEmpty()) {
//			catalogs = Collections.emptyList();
//		} else {
//			List<String> notFoundCatalogKeys = null;
//			catalogs = new ArrayList<>();
//			for (String catalogKey: keys) {
//				Optional<T> catalog = keyToRelated.apply(catalogKey);
//				if (! catalog.isPresent()) {
//					if (notFoundCatalogKeys == null) {
//						notFoundCatalogKeys = new ArrayList<>();
//					}
//					notFoundCatalogKeys.add(catalogKey);
//				} else {
//					catalogs.add(catalog.get());
//				}
//			}
//		}
//		return catalogs;
//	}

	public Iterable<Schema> findAllSchemas() {
		return schemaRepository.findAll();
	}

	public Optional<Schema> findSchemaByKey(String schemaKey) {
		return schemaRepository.findByKey(schemaKey);
	}

	public Optional<Schema> deleteSchemaByKey(String schemaKey) {
		Optional<Schema> schema = schemaRepository.findByKey(schemaKey);
		if (schema.isPresent()) {
			schemaRepository.delete(schema.get());
		}
		return schema;
	}

	public Schema createSchema(String schemaKey) {
		Schema schema = Schema.builder().key(schemaKey).build();
		schemaRepository.save(schema);
		return schema;
	}
}
