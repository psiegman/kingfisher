package nl.siegmann.kingfisher.graphql.domain.contenttype;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.siegmann.kingfisher.graphql.domain.fielddefinition.FieldDefinition;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentType {

	private UUID id;
	
	private String key;

	private UUID schemaId;
	
	private String contentTypeKey;
	
	private String name;
	
	private String description;

	private List<FieldDefinition> fieldDefinitions;

	public String getId() {
		return id.toString();
	}

	public UUID getUUId() {
		return id;
	}
	

}
