package nl.siegmann.kingfisher.catalog;

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
import nl.siegmann.kingfisher.api.domain.Catalog;
import nl.siegmann.kingfisher.api.domain.Schema;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) //RANDOM_PORT)
@TestPropertySource(properties = { "management.port=0" })
public class CatalogControllerIntegrationTest {

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
	public void shouldReturnCatalogWhenPostingNewCatalog() throws Exception {
		// given
		this.testRestTemplate.postForObject(apiBase + "/schema", Schema.builder().key("test").build(), Schema.class);
		Catalog catalog = Catalog.builder().key("main").schema("test").name("A Test Catalog").build();

		// when
		Catalog newCatalog = this.testRestTemplate.postForObject(apiBase + "/catalog", catalog, Catalog.class);

		// then
		assertThat(newCatalog.getKey()).isEqualTo("main");
		assertThat(newCatalog.getName()).isEqualTo("A Test Catalog");
	}

}