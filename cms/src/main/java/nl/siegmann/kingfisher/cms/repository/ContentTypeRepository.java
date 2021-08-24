package nl.siegmann.kingfisher.cms.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import nl.siegmann.kingfisher.cms.domain.ContentType;

public interface ContentTypeRepository extends PagingAndSortingRepository<ContentType, UUID> {

	Optional<ContentType> findBySchemaKeyAndKey(String schemaKey, String key);

	Iterable<ContentType> findAllBySchemaKey(String schemaKey);

	Iterable<ContentType> findAllBySchemaKeyAndKey(String schemaKey, String key);

	Iterable<ContentType> findAllBySchemaId(UUID schemaId);

	Optional<ContentType> findOneBySchemaIdAndKey(UUID schemaId, String contentTypeKey);

}