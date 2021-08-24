package nl.siegmann.kingfisher.graphql.domain.catalogversion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCatalogVersionPayload {
	private CatalogVersion catalogVersion;
}
