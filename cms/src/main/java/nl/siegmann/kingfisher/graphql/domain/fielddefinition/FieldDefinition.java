package nl.siegmann.kingfisher.graphql.domain.fielddefinition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.siegmann.kingfisher.graphql.domain.contenttype.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldDefinition {

	private String key;

	private String contentTypeKey;
	
	private String schemaKey;
	
	private String name;
	
	private String description;
	
	private FieldType fieldType;
}
