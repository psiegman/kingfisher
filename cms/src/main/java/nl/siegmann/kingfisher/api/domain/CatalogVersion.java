package nl.siegmann.kingfisher.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogVersion {

	private String key;

	private String name;
	
	private String description;

	private String schema;

	private String catalog;
	
	private int nrContentItems;
}
