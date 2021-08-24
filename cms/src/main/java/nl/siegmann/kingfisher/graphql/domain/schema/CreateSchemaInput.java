package nl.siegmann.kingfisher.graphql.domain.schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSchemaInput {
	private String key;
	private String name;
	private String description;
}
