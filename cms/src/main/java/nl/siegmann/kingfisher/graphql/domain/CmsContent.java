package nl.siegmann.kingfisher.graphql.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import nl.siegmann.kingfisher.graphql.domain.schema.Schema;

@Data
@Builder
public class CmsContent {
	private List<Schema> schemas;
}
