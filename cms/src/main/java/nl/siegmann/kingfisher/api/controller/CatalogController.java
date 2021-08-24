package nl.siegmann.kingfisher.api.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.siegmann.kingfisher.api.converter.CatalogConverter;
import nl.siegmann.kingfisher.api.domain.Catalog;
import nl.siegmann.kingfisher.cms.exception.RelatedObjectNotFoundException;
import nl.siegmann.kingfisher.cms.service.CatalogService;
import nl.siegmann.kingfisher.util.CollectionUtil;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private CatalogConverter catalogConverter;
	
	@RequestMapping("/{schemaKey}")
	public Map<String, Iterable<Catalog>> getCatalogs(@PathVariable String schemaKey) {
		return CollectionUtil.convertToMap("catalogs", catalogService.findAllBySchemaKey(schemaKey), catalogConverter);
	}

	@GetMapping("/{schemaKey}/{catalogKey}")
	public Map<String, Optional<Catalog>> getCatalog(@PathVariable String schemaKey, @PathVariable String catalogKey) {
		return CollectionUtil.convertToMap("catalog", catalogService.findBySchemaKeyAndKey(schemaKey, catalogKey), catalogConverter);
	}

	@PostMapping("")
	public Map<String, Catalog> addCatalog(@RequestBody Catalog catalog) throws RelatedObjectNotFoundException {
		return CollectionUtil.singletonMap("catalog", catalogConverter.apply(catalogService.save(catalog)));
//		Optional<Schema> schema = schemaRepository.findByKey(schemaKey);
//		// XXX check schema.isPresent()
//		catalog.setSchema(schema.get());
//		return catalogRepository.save(catalog);
	}

//	@PutMapping("/{schemaKey}/{catalogKey}")
//	public Catalog addCatalog(@PathVariable String schemaKey, @PathVariable String catalogKey) {
//		Optional<Schema> schema = schemaRepository.findByKey(schemaKey);
//		// XXX check schema.isPresent()
//		Catalog catalog = Catalog.builder().key(catalogKey).schema(schema.get()).build();
//		return catalogRepository.save(catalog);
//	}
}
