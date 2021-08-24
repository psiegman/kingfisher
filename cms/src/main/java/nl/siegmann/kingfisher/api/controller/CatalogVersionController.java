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

import nl.siegmann.kingfisher.api.converter.CatalogVersionConverter;
import nl.siegmann.kingfisher.api.domain.CatalogVersion;
import nl.siegmann.kingfisher.cms.exception.RelatedObjectNotFoundException;
import nl.siegmann.kingfisher.cms.service.CatalogVersionService;
import nl.siegmann.kingfisher.util.CollectionUtil;

@RestController
@RequestMapping("/api/v1/catalogversion")
public class CatalogVersionController {

	@Autowired
	private CatalogVersionService catalogVersionService;

	@Autowired
	private CatalogVersionConverter catalogVersionConverter;
	
	@RequestMapping("/{schemaKey}/{catalogKey}")
	public Map<String, Iterable<CatalogVersion>> getCatalogVersions(@PathVariable String schemaKey,
			@PathVariable String catalogKey) {
		return CollectionUtil.convertToMap("catalogVerions", catalogVersionService.findAllBySchemaKeyAndCatalogKey(schemaKey, catalogKey), catalogVersionConverter);
	}

	@GetMapping("/{schemaKey}/{catalogKey}/{catalogVersionKey}")
	public Map<String, Optional<CatalogVersion>> getCatalog(@PathVariable String schemaKey, @PathVariable String catalogKey,
			@PathVariable String catalogVersionKey) {
		return CollectionUtil.convertToMap("catalogVersions",  catalogVersionService.findBySchemaKeyAndCatalogKeyAndKey(schemaKey, catalogKey, catalogVersionKey), catalogVersionConverter);
	}

	@PostMapping("")
	public Map<String, CatalogVersion> addCatalog(@RequestBody CatalogVersion catalogVersion) throws RelatedObjectNotFoundException {
		return CollectionUtil.singletonMap("catalogVersion", catalogVersionConverter.apply(catalogVersionService.save(catalogVersion)));
	}
}
