package nl.siegmann.kingfisher.api.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.siegmann.kingfisher.api.converter.ContentItemConverter;
import nl.siegmann.kingfisher.api.domain.ContentItem;
import nl.siegmann.kingfisher.cms.service.ContentItemService;
import nl.siegmann.kingfisher.util.CollectionUtil;

@RestController
@RequestMapping("/api/v1/contentitem")
public class ContentItemController {

	@Autowired
	private ContentItemService contentItemService;

	@Autowired
	private ContentItemConverter contentItemConverter;
	
	@GetMapping(value = "/{schemaKey}/{catalogKey}/{catalogVersionKey}/{contentItemKey:.+}")
	public Map<String, Optional<ContentItem>> getCatalogVersions(@PathVariable String schemaKey,
			@PathVariable String catalogKey, @PathVariable String catalogVersionKey, @PathVariable String contentItemKey) {
		Optional<nl.siegmann.kingfisher.cms.domain.ContentItem> contentItem = contentItemService.findBySchemaKeyAndCatalogKeyAndCatalogVersionKeyAndKey(schemaKey, catalogKey,catalogVersionKey, contentItemKey);
		return CollectionUtil.convertToMap("contentItem", contentItem, contentItemConverter);
	}

//	@GetMapping("/{schemaKey}/{catalogKey}/{catalogVersionKey}")
//	public Map<String, Optional<CatalogVersion>> getCatalog(@PathVariable String schemaKey, @PathVariable String catalogKey,
//			@PathVariable String catalogVersionKey) {
//		return CollectionUtil.convertToMap("catalogVersions",  catalogVersionService.findBySchemaKeyAndCatalogKeyAndKey(schemaKey, catalogKey, catalogVersionKey), catalogVersionConverter);
//	}
//
//	@PostMapping("")
//	public Map<String, CatalogVersion> addCatalog(@RequestBody CatalogVersion catalogVersion) throws RelatedObjectNotFoundException {
//		return CollectionUtil.singletonMap("catalogVersion", catalogVersionConverter.apply(catalogVersionService.save(catalogVersion)));
//	}
}
