package nl.siegmann.kingfisher.schema;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.siegmann.kingfisher.cms.domain.Schema;
import nl.siegmann.kingfisher.cms.repository.SchemaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class SchemaRepositoryIntegrationTest {

	@Autowired
	private SchemaRepository schemaRepository;

	public static Schema createTestSchema() {
		Schema schema = Schema
				.builder()
				.key("test-schema")
				.build();
		return schema;
	}
	
	@Test
	public void givenCollectionRepository_whenSaveAndRetreiveEntity_thenOK() {

		Schema savedSchema = schemaRepository.save(createTestSchema());

		Optional<Schema> foundSchema = schemaRepository.findByKey("test-schema");

		assertThat(foundSchema.isPresent()).isTrue();
		assertThat(foundSchema.get().getKey()).isEqualTo("test-schema");
		assertThat(foundSchema.get().getKey()).isEqualTo(savedSchema.getKey());

//		assertThat(schemaRepository.findByKey("_kingfisher_admin").isPresent()).isTrue();
	}
}