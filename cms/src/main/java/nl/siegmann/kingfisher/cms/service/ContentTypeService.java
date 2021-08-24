package nl.siegmann.kingfisher.cms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.siegmann.kingfisher.cms.domain.ContentType;
import nl.siegmann.kingfisher.cms.domain.FieldDefinition;
import nl.siegmann.kingfisher.cms.domain.FieldType;
import nl.siegmann.kingfisher.cms.domain.Schema;
import nl.siegmann.kingfisher.cms.exception.RelatedObjectNotFoundException;
import nl.siegmann.kingfisher.cms.repository.ContentTypeRepository;
import nl.siegmann.kingfisher.cms.repository.FieldDefinitionRepository;
import nl.siegmann.kingfisher.cms.repository.SchemaRepository;

@Service
public class ContentTypeService {

	@Autowired
	private SchemaRepository schemaRepository;

	@Autowired
	private ContentTypeRepository contentTypeRepository;

	@Autowired
	private FieldDefinitionRepository fieldDefinitionRepository;

	private static final Function<nl.siegmann.kingfisher.api.domain.FieldDefinition, FieldDefinition> fieldDefinitionConverter = new Function<nl.siegmann.kingfisher.api.domain.FieldDefinition, FieldDefinition>(){

		@Override
		public FieldDefinition apply(nl.siegmann.kingfisher.api.domain.FieldDefinition source) {
			// @formatter:off
			return FieldDefinition.builder()
					.key(source.getKey())
					.name(source.getName())
					.description(source.getDescription())
					.fieldType(FieldType.valueOf(source.getFieldType().toString().toUpperCase()))
				.build();
			// @formatter:on
		}
	};

	public ContentType save(nl.siegmann.kingfisher.api.domain.ContentType source) throws RelatedObjectNotFoundException {
		Optional<Schema> schema = schemaRepository.findByKey(source.getSchema());
		if (!schema.isPresent()) {
			throw new RelatedObjectNotFoundException();
		}

		// @formatter:off
		ContentType target = ContentType.builder()
				.key(source.getKey())
				.schema(schema.get())
				.name(source.getName())
				.description(source.getDescription())
				.build();
		// @formatter:on
//		source.getFieldDefinitions().stream().map(fieldDefinitionConverter).map(f -> {f.setContentType(target);return f}).collect(Collectors.toList());
		List<FieldDefinition> targetFieldDefinitions = new ArrayList<>();
		for (nl.siegmann.kingfisher.api.domain.FieldDefinition fieldDefinition: source.getFieldDefinitions()) {
			FieldDefinition targetFieldDefinition = fieldDefinitionConverter.apply(fieldDefinition);
			targetFieldDefinition.setContentType(target);
			targetFieldDefinitions.add(targetFieldDefinition);
			targetFieldDefinition.setSequence(targetFieldDefinitions.size());
		}
//		source.getFieldDefinitions().stream().map(fieldDefinitionConverter);//.forEach(f -> {f.setContentType(target);targetFieldDefinitions.add(f);
		target.setFieldDefinitions(targetFieldDefinitions);
		return contentTypeRepository.save(target);
	}

	public Iterable<ContentType> findAllBySchemaKey(String schemaKey) {
		return contentTypeRepository.findAllBySchemaKey(schemaKey);
	}

	public Iterable<ContentType> findAllBySchemaKeyAndKey(String schemaKey, String contentTypeKey) {
		return contentTypeRepository.findAllBySchemaKeyAndKey(schemaKey, contentTypeKey);
	}


	public Optional<ContentType> findBySchemaKeyAndKey(String schemaKey,
			String contentTypeKey) {
		return contentTypeRepository.findBySchemaKeyAndKey(schemaKey, contentTypeKey);
	}

	public Optional<ContentType> deleteBySchemaKeyAndKey(String schemaKey, String key) {
		Optional<ContentType> catalog = contentTypeRepository.findBySchemaKeyAndKey(schemaKey, key);
		if (catalog.isPresent()) {
			contentTypeRepository.delete(catalog.get());
		}
		return catalog;
	}

	public ContentType createContentType(String schemaKey, String key) throws RelatedObjectNotFoundException {
		Optional<Schema> schema = schemaRepository.findByKey(schemaKey);
		if (!schema.isPresent()) {
			throw new RelatedObjectNotFoundException();
		}
		ContentType catalog = ContentType.builder().key(key).schema(schema.get()).build();
		contentTypeRepository.save(catalog);
		return catalog;
	}

	public Iterable<FieldDefinition> findAllBySchemaKeyAndCatalogKey(String schemaKey,
			String contentTypeKey) {
		return fieldDefinitionRepository.findAllBySchemaKeyAndContentTypeKey(schemaKey, contentTypeKey);
	}

	public Optional<FieldDefinition> findBySchemaKeyAndCatalogKeyAndKey(String schemaKey, String contentTypeKey, String fieldDefinitionKey) {
		return fieldDefinitionRepository.findBySchemaKeyAndContentTypeKeyAndKey(schemaKey, contentTypeKey, fieldDefinitionKey);
	}

	public FieldDefinition createFieldDefinition(String schemaKey, String contentTypeKey, String key) throws RelatedObjectNotFoundException {
		Optional<ContentType> contentType = findBySchemaKeyAndKey(schemaKey, contentTypeKey);
		if (!contentType.isPresent()) {
			throw new RelatedObjectNotFoundException();
		}

		FieldDefinition fieldDefinition = FieldDefinition.builder().contentType(contentType.get()).key(key).build();
		fieldDefinitionRepository.save(fieldDefinition);
		return fieldDefinition;
	}

	public Optional<FieldDefinition> deleteBySchemaKeyAndCatalogKeyAndKey(String schemaKey, String contentTypeKey,
			String key) {
		Optional<FieldDefinition> fieldDefinition = fieldDefinitionRepository.findBySchemaKeyAndContentTypeKeyAndKey(schemaKey, contentTypeKey, key);
		if (fieldDefinition.isPresent()) {
			fieldDefinitionRepository.delete(fieldDefinition.get());
		}
		return fieldDefinition;
	}

	public ContentType findOne(UUID contentTypeId) {
		return contentTypeRepository.findOne(contentTypeId);
	}

	public Iterable<ContentType> findAllBySchemaId(UUID schemaId) {
		return contentTypeRepository.findAllBySchemaId(schemaId);
	}

	public Optional<ContentType> findOneBySchemaIdAndKey(UUID schemaId, String contentTypeKey) {
		return contentTypeRepository.findOneBySchemaIdAndKey(schemaId, contentTypeKey);
	}
}
