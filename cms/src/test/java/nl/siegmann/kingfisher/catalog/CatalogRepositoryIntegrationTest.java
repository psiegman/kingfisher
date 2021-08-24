package nl.siegmann.kingfisher.catalog;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.siegmann.kingfisher.cms.domain.Catalog;
import nl.siegmann.kingfisher.cms.domain.CatalogVersion;
import nl.siegmann.kingfisher.cms.domain.Schema;
import nl.siegmann.kingfisher.cms.repository.CatalogRepository;
import nl.siegmann.kingfisher.cms.repository.SchemaRepository;
import nl.siegmann.kingfisher.schema.SchemaRepositoryIntegrationTest;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class CatalogRepositoryIntegrationTest {

	@Autowired
	private CatalogRepository catalogRepository;

	@Autowired
	private SchemaRepository schemaRepository;
	
	public static Catalog createTestCatalog(Schema schema) {
		Catalog catalog = Catalog
				.builder()
				.key("test-catalog")
				.schema(schema)
				.build();

			catalog.setCatalogVersions(Arrays.asList(
					CatalogVersion.builder().catalog(catalog).key("draft").build(),
					CatalogVersion.builder().catalog(catalog).key("online").build()
				));
		return catalog;
	}
	
	@Test
	public void givenCollectionRepository_whenSaveAndRetreiveEntity_thenOK() {

		Schema schema = schemaRepository.save(SchemaRepositoryIntegrationTest.createTestSchema());
		Catalog savedCatalog = catalogRepository.save(createTestCatalog(schema));

		Optional<Catalog> foundCatalog = catalogRepository.findBySchemaKeyAndKey(schema.getKey(), "test-catalog");

		assertThat(foundCatalog.isPresent()).isTrue();
		assertThat(foundCatalog.get().getKey()).isEqualTo("test-catalog");
		assertThat(foundCatalog.get().getKey()).isEqualTo(savedCatalog.getKey());
		assertThat(foundCatalog.get().getCatalogVersions().size()).isEqualTo(2);
		assertThat(foundCatalog.get().getCatalogVersions().get(0).getCatalog()).isNotNull();
	}
}