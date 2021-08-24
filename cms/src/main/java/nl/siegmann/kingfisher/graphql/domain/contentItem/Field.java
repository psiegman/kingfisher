package nl.siegmann.kingfisher.graphql.domain.contentitem;

import lombok.Builder;
import lombok.Data;
import nl.siegmann.kingfisher.graphql.domain.fielddefinition.FieldDefinition;

@Data
@Builder
public class Field {
	private String key;
	private FieldDefinition fieldDefinition;
	private FieldValue value;
}
