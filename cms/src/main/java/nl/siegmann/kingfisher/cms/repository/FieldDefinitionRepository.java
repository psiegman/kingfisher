package nl.siegmann.kingfisher.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import nl.siegmann.kingfisher.cms.domain.FieldDefinition;

public interface FieldDefinitionRepository extends PagingAndSortingRepository<FieldDefinition, Long> {

	@Query("select f from FieldDefinition f join f.contentType c join c.schema s where s.key = ?1 and c.key = ?2")
	Iterable<FieldDefinition> findAllBySchemaKeyAndContentTypeKey(String schemaKey, String contentTypeKey);

	@Query("select f from FieldDefinition f join f.contentType c join c.schema s where s.key = ?1 and c.key = ?2 and f.key = ?3")
	Optional<FieldDefinition> findBySchemaKeyAndContentTypeKeyAndKey(String schemaKey, String contentTypeKey,
			String fieldDefinitionKey);

}
