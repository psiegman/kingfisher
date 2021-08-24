package nl.siegmann.kingfisher.api.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentType {

	private String key;

	private String schema;
	
	private String name;
	
	private String description;

	private List<FieldDefinition> fieldDefinitions;
}
