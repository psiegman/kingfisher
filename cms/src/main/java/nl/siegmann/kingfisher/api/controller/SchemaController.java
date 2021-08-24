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

import nl.siegmann.kingfisher.api.converter.SchemaConverter;
import nl.siegmann.kingfisher.api.domain.Schema;
import nl.siegmann.kingfisher.cms.exception.RelatedObjectNotFoundException;
import nl.siegmann.kingfisher.cms.service.SchemaService;
import nl.siegmann.kingfisher.util.CollectionUtil;

@RestController
@RequestMapping("/api/v1/schema")
public class SchemaController {

	@Autowired
	private SchemaService schemaService;

	@Autowired
	private SchemaConverter schemaConverter;

	@RequestMapping("")
	public Map<String, Iterable<Schema>> getSchemas() {
		return CollectionUtil.singletonMap("schemas", CollectionUtil.convert(schemaService.findAll(), schemaConverter));
	}

	@GetMapping("/{key}")
	public Map<String, Optional<Schema>> getSchema(@PathVariable String key) {
		return CollectionUtil.singletonMap("schema",
				CollectionUtil.convert(schemaService.findSchemaByKey(key), schemaConverter));
	}

	@PostMapping("")
	public Map<String, Schema> addSchema(@RequestBody Schema schema) throws RelatedObjectNotFoundException {
		return CollectionUtil.singletonMap("schema", schemaConverter.apply(schemaService.save(schema)));
	}

	// @PutMapping("/{key}")
	// public Schema addSchema(@PathVariable String key, @RequestBody Schema schema)
	// {
	// if (schema == null) {
	// schema = Schema.builder().key(key).build();
	// }
	// return schemaRepository.save(schema);
	// }
}
