package nl.siegmann.kingfisher.graphql.domain.contentitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFieldInput {

	private String schemaKey;
	private String catalogKey;
	private String catalogVersionKey;
	private String contentItemKey;
	private String fieldKey;
}
