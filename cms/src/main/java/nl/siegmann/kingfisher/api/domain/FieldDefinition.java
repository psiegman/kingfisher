package nl.siegmann.kingfisher.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FieldDefinition {

	private String key;

	private String name;
	
	private String description;
	
	private FieldType fieldType;
}
