package nl.siegmann.kingfisher.cms.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import nl.siegmann.kingfisher.cms.domain.CatalogVersion;
import nl.siegmann.kingfisher.cms.domain.ContentItem;

public interface ContentItemRepository extends PagingAndSortingRepository<ContentItem, UUID> {

	Optional<ContentItem> findByCatalogVersionAndKey(@Param("catalogVersion") CatalogVersion catalogVersion, @Param("key") String key);

	@Query("select i from ContentItem i join i.catalogVersion v join v.catalog c join c.schema s where s.key = ?1 and c.key = ?2 and v.key = ?3 and i.key = ?4")
	Optional<ContentItem> findBySchemaKeyAndCatalogKeyAndCatalogVersionKeyAndKey(String schemaKey, String catalogKey,
			String catalogVersionKey, String contentItemKey);

	int countByCatalogVersion(CatalogVersion catalogVersion);

	@Query("select i from ContentItem i join i.catalogVersion v join v.catalog c join c.schema s where s.key = ?1 and c.key = ?2 and v.key = ?3")
	Iterable<ContentItem> findBySchemaKeyAndCatalogKeyAndCatalogVersionKey(String schemaKey, String catalogKey, String catalogVersionKey);

	@Query("select count(i) from ContentItem i join i.catalogVersion v join v.catalog c join c.schema s where s.key = ?1 and c.key = ?2 and v.key = ?3")
	int countBySchemaKeyAndCatalogKeyAndCatalogVersionKey(String schemaKey, String catalogKey,
			String catalogVersionKey);

	int countByCatalogVersionId(UUID catalogVersionId);

	Iterable<ContentItem> findByCatalogVersionId(UUID catalogVersionId);

	Optional<ContentItem> findOneByCatalogVersionIdAndKey(UUID catalogVersionId, String contentItemKey);

}