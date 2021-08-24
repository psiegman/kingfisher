package nl.siegmann.kingfisher.graphql.domain.contentitem;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import nl.siegmann.kingfisher.cms.domain.ContentItem;
import nl.siegmann.kingfisher.cms.domain.FieldType;
import nl.siegmann.kingfisher.cms.domain.StringField;
import nl.siegmann.kingfisher.cms.domain.TextField;
import nl.siegmann.kingfisher.cms.service.ContentItemService;
import nl.siegmann.kingfisher.graphql.converter.GQLContentItemConverter;

@Component
public class ContentItemMutationResolver implements GraphQLMutationResolver {

	@Autowired
	private ContentItemService contentItemService;

	@Autowired
	private GQLContentItemConverter contentItemConverter;

	@Transactional
	public UpdateFieldPayload updateField(UpdateFieldInput contentItemUpdate) {
		Optional<ContentItem> contentItem = contentItemService.findBySchemaKeyAndCatalogKeyAndCatalogVersionKeyAndKey(
				contentItemUpdate.getSchemaKey(), contentItemUpdate.getCatalogKey(), contentItemUpdate.getCatalogVersionKey(),
				contentItemUpdate.getFieldKey());
		if (! contentItem.isPresent()) {
			return UpdateFieldPayload.builder().build();
		} else {
			return UpdateFieldPayload.builder().contentItem(contentItemConverter.apply(contentItem.get())).build();
		}
	}

	@Transactional
	public UpdateContentItemPayload updateContentItem(UpdateContentItemInput contentItemUpdate) {
		Optional<ContentItem> optionalContentItem = contentItemService.findOneBySchemaKeyAndCatalogKeyAndCatalogVersionKeyAndKey(
				contentItemUpdate.getSchemaKey(), contentItemUpdate.getCatalogKey(), contentItemUpdate.getCatalogVersionKey(), contentItemUpdate.getContentItemKey());
		if (! optionalContentItem.isPresent()) {
			return UpdateContentItemPayload.builder().build();
		}
		ContentItem contentItem = optionalContentItem.get();
		contentItemUpdate.getFields().forEach(fieldUpdate -> {
			Optional<nl.siegmann.kingfisher.cms.domain.Field> optionalField = contentItem.getFieldByKey(fieldUpdate.getFieldKey());
			if (optionalField.isPresent()) {
				nl.siegmann.kingfisher.cms.domain.Field dbField = optionalField.get();
				if (dbField.getFieldDefinition().getFieldType() == FieldType.STRING) {
					((StringField) dbField).setValue(fieldUpdate.getFieldValue());
				} else if (dbField.getFieldDefinition().getFieldType() == FieldType.TEXT) {
					((TextField) dbField).setValue(fieldUpdate.getFieldValue());
				}
			}
		});
//		contentItemService.save(contentItem);
		return UpdateContentItemPayload.builder().contentItem(contentItemConverter.apply(optionalContentItem.get())).build();
	}
}
