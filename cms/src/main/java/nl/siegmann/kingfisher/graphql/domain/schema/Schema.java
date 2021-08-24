package nl.siegmann.kingfisher.graphql.domain.schema;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schema {

	private UUID id;
	
	public String getId() {
		return id.toString();
	}

	public UUID getUUId() {
		return id;
	}
	
	private String key;

	private String name;

	private String description;
}
