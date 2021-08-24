package nl.siegmann.kingfisher.graphql.domain.catalog;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Catalog {

	private UUID id;
	
	public String getId() {
		return id.toString();
	}

	public UUID getUUId() {
		return id;
	}
	
	private UUID schemaId;

	private String key;

	private String name;

	private String description;
}
