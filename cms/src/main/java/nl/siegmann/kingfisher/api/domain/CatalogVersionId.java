package nl.siegmann.kingfisher.api.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CatalogVersionId {
	private String schemaKey;
	private String catalogKey;
	private String catalogVersionKey;
}
