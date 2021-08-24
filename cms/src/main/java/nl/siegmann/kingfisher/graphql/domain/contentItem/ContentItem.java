package nl.siegmann.kingfisher.graphql.domain.contentitem;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentItem {

	private UUID id;
	private UUID contentTypeId;
	private UUID catalogVersionId;
	private String key;
	private String name;
	private String description;
	private List<Field> fields;

	public String getId() {
		return id.toString();
	}

	public UUID getUUId() {
		return id;
	}
}
