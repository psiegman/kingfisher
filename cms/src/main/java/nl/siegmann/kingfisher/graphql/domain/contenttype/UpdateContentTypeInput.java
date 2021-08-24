package nl.siegmann.kingfisher.graphql.domain.contenttype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContentTypeInput {
	private String key;
	private String schemaKey;
	private String name;
	private String description;
}
