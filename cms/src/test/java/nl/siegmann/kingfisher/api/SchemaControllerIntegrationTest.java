package nl.siegmann.kingfisher.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import nl.siegmann.kingfisher.CmsApplication;
import nl.siegmann.kingfisher.api.domain.Schema;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) //RANDOM_PORT)
@TestPropertySource(properties = { "management.port=0" })
public class SchemaControllerIntegrationTest {

//	@LocalServerPort
	private int port = 8081;

	@Value("${local.management.port}")
	private int mgt;

	private String apiBase;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Before
	public void setUp() {
		this.apiBase = "http://localhost:" + this.port + "/api/v1";
	}
	
	@Test
	public void shouldReturnSchemaWhenPostingNewSchema() throws Exception {
		// given
		Schema schema = Schema.builder().key("test").name("A Test Schema").build();

		// when
		Schema newSchema = this.testRestTemplate.postForObject(apiBase + "/schema", schema, Schema.class);

		// then
		assertThat(newSchema.getKey()).isEqualTo("test");
		assertThat(newSchema.getName()).isEqualTo("A Test Schema");
	}
	
	
//	public void shouldReturn200WhenSendingRequestToController() throws Exception {
//		ResponseEntity<Schema[]> warmup = this.testRestTemplate.getForEntity(apiBase + "/schema", Schema[].class);
//		
//		Schema schema = Schema.builder().key("test").name("A Test Schema").build();
//		Schema newSchema = this.testRestTemplate.postForObject(apiBase + "/schema", schema, Schema.class);
//		assertThat(newSchema.getKey()).isEqualTo("test");
//		assertThat(newSchema.getName()).isEqualTo("A Test Schema");
//
//		ResponseEntity<Schema[]> result = this.testRestTemplate.getForEntity(apiBase + "/schema", Schema[].class);
//		Schema[] schemas = result.getBody();
//		assertThat(schemas.length).isEqualTo(1);
//		Schema schema_1 = schemas[0];
//		assertThat(schema_1.getKey()).isEqualTo("test");
//		assertThat(schema_1.getName()).isEqualTo("A Test Schema");
//	}

//	@Test
//	public void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
//		@SuppressWarnings("rawtypes")
//		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity("http://localhost:" + this.mgt + "/info",
//				Map.class);
//
//		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//	}

}