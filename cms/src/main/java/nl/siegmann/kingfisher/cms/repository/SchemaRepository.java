package nl.siegmann.kingfisher.cms.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import nl.siegmann.kingfisher.cms.domain.Schema;

public interface SchemaRepository extends PagingAndSortingRepository<Schema, UUID> {

	Optional<Schema> findByKey(String key);
}
