package nl.siegmann.kingfisher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.jooq.tools.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import lombok.extern.slf4j.Slf4j;
import nl.siegmann.kingfisher.api.domain.Catalog;
import nl.siegmann.kingfisher.api.domain.CatalogVersion;
import nl.siegmann.kingfisher.api.domain.ContentItem;
import nl.siegmann.kingfisher.api.domain.ContentType;
import nl.siegmann.kingfisher.api.domain.Schema;
import nl.siegmann.kingfisher.cms.exception.RelatedObjectNotFoundException;
import nl.siegmann.kingfisher.cms.service.CatalogService;
import nl.siegmann.kingfisher.cms.service.CatalogVersionService;
import nl.siegmann.kingfisher.cms.service.ContentItemService;
import nl.siegmann.kingfisher.cms.service.ContentTypeService;
import nl.siegmann.kingfisher.cms.service.SchemaService;


@Component
@Slf4j
public class InitializeKingfisher {

	@Autowired
	private SchemaService schemaService;

	@Autowired
	private CatalogService catalogService;

	@Autowired
	private CatalogVersionService catalogVersionService;

	@Autowired
	private ContentTypeService contentTypeService;

	@Autowired
	private ContentItemService contentItemService;

	private ObjectMapper jsonObjectMapper;

	// private static final Logger LOG =
	// LoggerFactory.getLogger(InitializeKingfisher.class);

	@Value("${kingfisher.createAdminContentOnEmpty:true}")
	private boolean createKingfisherAdminContextOnEmpty;

	@EventListener
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (!createKingfisherAdminContextOnEmpty) {
			return;
		}

		if (schemaService.findAll().iterator().hasNext()) {
			return;
		}

		try {
			loadInitialData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try {
		// schemaService.save(Schema.builder().key("_kingfisher").name("Kingfisher
		// Schema").build());
		// catalogService.save(Catalog.builder().schema("_kingfisher").key("admin").name("Kingfisher
		// Admin Content")
		// .description("The content for the Kingfisher admin application").build());
		// catalogVersionService
		// .save(CatalogVersion.builder().schema("_kingfisher").catalog("admin").key("draft").build());
		// catalogVersionService
		// .save(CatalogVersion.builder().schema("_kingfisher").catalog("admin").key("published").build());
		// contentTypeService.save(ContentType.builder().key("article").schema("_kingfisher")
		// .fieldDefinitions(Arrays.asList(FieldDefinition.builder().key("title").fieldType("string").build(),
		// FieldDefinition.builder().key("author").fieldType("string").build()))
		// .build());
		// } catch (RelatedObjectNotFoundException e) {
		// log.error(e.getMessage(), e);
		// }
	}

	private void loadInitialData() throws IOException {
		String[] contentType = new String[1];
		Map<String, JsonImporter> jsonImporters = new HashMap<>();
		jsonImporters.put(Schema.class.getSimpleName(), new SchemaImporter());
		jsonImporters.put(Catalog.class.getSimpleName(), new CatalogImporter());
		jsonImporters.put(CatalogVersion.class.getSimpleName(), new CatalogVersionImporter());
		jsonImporters.put(ContentType.class.getSimpleName(), new ContentTypeImporter());
		jsonImporters.put(ContentItem.class.getSimpleName(), new ContentItemImporter());

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(InitializeKingfisher.class.getResourceAsStream("/data/initial_data.txt")));

		String line;

		while ((line = bufferedReader.readLine()) != null) {
			if (StringUtils.isBlank(line)) {
				// ignore
			} else if (!line.startsWith("{")) {
				contentType[0] = line.trim();
			} else {
				try {
					jsonImporters.get(contentType[0]).importJson(line);
				} catch (IOException | RelatedObjectNotFoundException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
	}

	private ObjectMapper getObjectMapper() {
		if(this.jsonObjectMapper == null)  {
			this.jsonObjectMapper = new ObjectMapper();
			SimpleModule module = new SimpleModule();
			module.addDeserializer(ContentItem.class, contentItemService.getJsonDeserializer());
			jsonObjectMapper.registerModule(module);
		}
		return jsonObjectMapper;
	}
	
	
	private interface JsonImporter {
		void importJson(String content)
				throws JsonParseException, JsonMappingException, IOException, RelatedObjectNotFoundException;
	}

	private class SchemaImporter implements JsonImporter {
		@Override
		public void importJson(String content)
				throws JsonParseException, JsonMappingException, IOException, RelatedObjectNotFoundException {
			Schema schema = getObjectMapper().readValue(content, Schema.class);
			schemaService.save(schema);
		}
	}

	private class CatalogImporter implements JsonImporter {
		@Override
		public void importJson(String content)
				throws JsonParseException, JsonMappingException, IOException, RelatedObjectNotFoundException {
			Catalog catalog = getObjectMapper().readValue(content, Catalog.class);
			catalogService.save(catalog);
		}
	}

	private class CatalogVersionImporter implements JsonImporter {
		@Override
		public void importJson(String content)
				throws JsonParseException, JsonMappingException, IOException, RelatedObjectNotFoundException {
			CatalogVersion catalogVersion = getObjectMapper().readValue(content, CatalogVersion.class);
			catalogVersionService.save(catalogVersion);
		}
	}

	private class ContentTypeImporter implements JsonImporter {
		@Override
		public void importJson(String content)
				throws JsonParseException, JsonMappingException, IOException, RelatedObjectNotFoundException {
			ContentType contentType = getObjectMapper().readValue(content, ContentType.class);
			contentTypeService.save(contentType);
		}
	}

	private class ContentItemImporter implements JsonImporter {
		@Override
		public void importJson(String content)
				throws JsonParseException, JsonMappingException, IOException, RelatedObjectNotFoundException {
			ContentItem contentItem = getObjectMapper().readValue(content, ContentItem.class);
			contentItemService.save(contentItem);
		}
	}

}