package nl.siegmann.kingfisher.cms.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import nl.siegmann.kingfisher.cms.domain.CatalogVersion;

public interface CatalogVersionRepository extends PagingAndSortingRepository<CatalogVersion, UUID> {

//	@Query("select v from CatalogVersion v join Catalog c on c = v.catalog join Schema s on s = c.schema where s.key = :#{#catalogVersionId.schemaKey} and c.key = :#{#catalogVersionId.catalogKey} and v.key = :#{#catalogVersionId.catalogVersionKey}")
//	Optional<CatalogVersion> findByCatalogVersionId(CatalogVersionId catalogVersionId);

//	@Query("select v from CatalogVersion v join Catalog c on c = v.catalog join Schema s on s = c.schema where s.key = ?1 and c.key = ?2 and v.key = ?3")
	@Query("select v from CatalogVersion v join v.catalog c join c.schema s where s.key = ?1 and c.key = ?2 and v.key = ?3")
	Optional<CatalogVersion> findBySchemaKeyAndCatalogKeyAndKey(String schemaKey, String catalogKey,
			String catalogVersionKey);

	@Query("select v from CatalogVersion v join v.catalog c join c.schema s where s.key = ?1 and c.key = ?2")
	Iterable<CatalogVersion> findAllBySchemaKeyAndCatalogKey(String schemaKey, String catalogKey);

	Iterable<CatalogVersion> findByCatalogId(UUID catalogId);

	Optional<CatalogVersion> findOneByCatalogIdAndKey(UUID catalogId, String catalogVersionKey);
}
