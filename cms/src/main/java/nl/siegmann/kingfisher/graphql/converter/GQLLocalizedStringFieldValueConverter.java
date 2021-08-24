package nl.siegmann.kingfisher.graphql.converter;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.siegmann.kingfisher.cms.domain.LocalizedStringField;
import nl.siegmann.kingfisher.cms.service.UserService;
import nl.siegmann.kingfisher.graphql.domain.contentitem.LocalizedString;
import nl.siegmann.kingfisher.graphql.domain.contentitem.LocalizedStringFieldValue;

@Component
public class GQLLocalizedStringFieldValueConverter
		implements Function<nl.siegmann.kingfisher.cms.domain.Field, LocalizedStringFieldValue> {

	@Autowired
	private UserService userService;
	
	@Override
	public LocalizedStringFieldValue apply(nl.siegmann.kingfisher.cms.domain.Field source) {
		if (!(source instanceof LocalizedStringField)) {
			return null;
		}
		LocalizedStringField localizedStringField = (LocalizedStringField) source;
		
		String currentLocaleContent = localizedStringField.getValues().get(userService.getCurrentUserContext().getLocale());
		
		LocalizedStringFieldValue localizedStringFieldValue = LocalizedStringFieldValue.builder().content(currentLocaleContent).build();
		localizedStringField.getValues().entrySet().forEach(localString -> localizedStringFieldValue.getContents().add(
				LocalizedString.builder().locale(localString.getKey().toString()).value(localString.getValue()).build()));
		return localizedStringFieldValue;
	}

}
