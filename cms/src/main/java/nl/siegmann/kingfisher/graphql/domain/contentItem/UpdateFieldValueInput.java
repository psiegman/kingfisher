package nl.siegmann.kingfisher.graphql.domain.contentitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFieldValueInput {

	private String fieldKey;
	private String fieldValue;
}
