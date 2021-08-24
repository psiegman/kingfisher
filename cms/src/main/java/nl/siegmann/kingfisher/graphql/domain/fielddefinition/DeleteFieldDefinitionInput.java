package nl.siegmann.kingfisher.graphql.domain.fielddefinition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteFieldDefinitionInput {
	private String key;
	private String schemaKey;
	private String contentTypeKey;
}
