package nl.siegmann.kingfisher.api.domain;

import javax.persistence.Lob;

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
	
	@Lob
	private String content;
}
