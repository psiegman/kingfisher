package nl.siegmann.kingfisher.graphql.domain.contentitem;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateContentItemInput {

	private String schemaKey;
	private String catalogKey;
	private String catalogVersionKey;
	private String contentItemKey;
	private List<UpdateFieldValueInput> fields;
}
