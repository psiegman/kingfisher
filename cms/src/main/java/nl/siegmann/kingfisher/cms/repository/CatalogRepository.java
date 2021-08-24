package nl.siegmann.kingfisher.cms.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import nl.siegmann.kingfisher.cms.domain.Catalog;

public interface CatalogRepository extends PagingAndSortingRepository<Catalog, UUID> {

	Iterable<Catalog> findAllBySchemaKey(String schemaKey);

	Optional<Catalog> findBySchemaKeyAndKey(String schemaKey, String key);

	Iterable<Catalog> findAllBySchemaId(UUID schemaId);

	Optional<Catalog> findOneBySchemaIdAndKey(UUID schemaId, String catalogKey);
}
