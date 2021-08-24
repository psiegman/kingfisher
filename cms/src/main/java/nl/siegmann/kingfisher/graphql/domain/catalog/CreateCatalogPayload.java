package nl.siegmann.kingfisher.graphql.domain.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCatalogPayload {
	private Catalog catalog;
}
