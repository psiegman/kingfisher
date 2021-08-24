package nl.siegmann.kingfisher.graphql.domain.contentitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TextFieldValue extends FieldValue {

	private String mimeType;
	private String content;
}
