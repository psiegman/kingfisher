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

import nl.siegmann.kingfisher.api.converter.ContentTypeConverter;
import nl.siegmann.kingfisher.api.domain.ContentType;
import nl.siegmann.kingfisher.cms.exception.RelatedObjectNotFoundException;
import nl.siegmann.kingfisher.cms.service.ContentTypeService;
import nl.siegmann.kingfisher.util.CollectionUtil;

@RestController
@RequestMapping("/api/v1/contenttype")
public class ContentTypeController {

	@Autowired
	private ContentTypeConverter contentTypeConverter;

	@Autowired
	private ContentTypeService contentTypeService;

	@RequestMapping("/{schemaKey}")
	public Map<String, Iterable<ContentType>> getContentTypes(@PathVariable String schemaKey) {
		return CollectionUtil.convertToMap("contentTypes", contentTypeService.findAllBySchemaKey(schemaKey),
				contentTypeConverter);
	}

	@GetMapping("/{schemaKey}/{contentTypeKey}")
	public Map<String, Optional<ContentType>> getContentType(@PathVariable String schemaKey,
			@PathVariable String contentTypeKey) {
		return CollectionUtil.convertToMap("contentType",
				contentTypeService.findBySchemaKeyAndKey(schemaKey, contentTypeKey), contentTypeConverter);
	}

	@PostMapping("/{schemaKey}")
	public Map<String, ContentType> addContentType(@RequestBody ContentType contentType)
			throws RelatedObjectNotFoundException {
		return CollectionUtil.singletonMap("contentType",
				contentTypeConverter.apply(contentTypeService.save(contentType)));
	}
}
