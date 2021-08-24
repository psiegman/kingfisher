package nl.siegmann.kingfisher.contenttype;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import nl.siegmann.kingfisher.cms.domain.ContentType;
import nl.siegmann.kingfisher.cms.domain.FieldDefinition;
import nl.siegmann.kingfisher.cms.domain.FieldType;
import nl.siegmann.kingfisher.cms.domain.Schema;
import nl.siegmann.kingfisher.cms.repository.ContentTypeRepository;
import nl.siegmann.kingfisher.cms.repository.SchemaRepository;
import nl.siegmann.kingfisher.schema.SchemaRepositoryIntegrationTest;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class ContentTypeRepositoryIntegrationTest {

	@Autowired
	private SchemaRepository schemaRepository;

	@Autowired
	private ContentTypeRepository contentTypeRepository;

	@Autowired
	private EntityManager entityManager;
	
	public static ContentType createTestContentType(Schema schema) {
		
		ContentType contentType = ContentType.builder().schema(schema).key("article").build();
		contentType.setFieldDefinitions(Arrays.asList(
				FieldDefinition.builder().contentType(contentType).key("title").fieldType(FieldType.L_STRING).sequence(2).build(),
				FieldDefinition.builder().contentType(contentType).key("author").fieldType(FieldType.STRING).sequence(1).build()
		));
		return contentType;
	}
	
	@Test
	public void givenCollectionRepository_whenSaveAndRetreiveEntity_thenOK() {
		Schema schema = SchemaRepositoryIntegrationTest.createTestSchema();
		schemaRepository.save(schema);
		contentTypeRepository.save(createTestContentType(schema));
		
		Optional<ContentType> article = contentTypeRepository.findBySchemaKeyAndKey("test-schema", "article");
		
		assertThat(article.isPresent()).isTrue();
		
		// clear cache on article to retrieve fieldDefinitions in order
		entityManager.refresh(article.get());
		
		assertThat(article.get().getKey()).isEqualTo("article");
		assertThat(article.get().getFieldDefinitions().size()).isEqualTo(2);
		assertThat(article.get().getFieldDefinitions().get(0).getKey()).isEqualTo("author");
		assertThat(article.get().getFieldDefinitions().get(1).getKey()).isEqualTo("title");
	}
}