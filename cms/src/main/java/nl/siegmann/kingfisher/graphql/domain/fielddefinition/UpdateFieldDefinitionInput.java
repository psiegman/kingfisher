package nl.siegmann.kingfisher.graphql.domain.fielddefinition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.siegmann.kingfisher.graphql.domain.contenttype.FieldType;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFieldDefinitionInput {
	private String key;
	private String schemaKey;
	private String contentTypeKey;
	private String name;
	private String description;
	private FieldType fieldType;
}
