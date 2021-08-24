package nl.siegmann.kingfisher.api.domain;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentItem {

	private String schema;
	private String catalog;
	private String catalogVersion;
	private String key;
	private String contentType;
	private String name;
	private String description;
	
	private Map<String, Field> fields;
}
