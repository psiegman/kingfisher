package nl.siegmann.kingfisher.graphql.domain.catalogversion;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogVersion {
	
	private UUID id;
	
	private UUID schemaId;
	
	private UUID catalogId;

	private String key;
	
	private String name;
	
	private String description;
	
	public String getId() {
		return id.toString();
	}

	public UUID getUUId() {
		return id;
	}
}
