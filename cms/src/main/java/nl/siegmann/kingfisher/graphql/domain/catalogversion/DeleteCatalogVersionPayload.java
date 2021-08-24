package nl.siegmann.kingfisher.graphql.domain.catalogversion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCatalogVersionPayload {
	private CatalogVersion catalogVersion;
}
