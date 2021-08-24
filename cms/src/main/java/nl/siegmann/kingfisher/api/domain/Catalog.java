package nl.siegmann.kingfisher.api.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Catalog {

	private String key;

	private String name;

	private String description;

	private String schema;

	@JsonProperty("catalogVersions")
	private List<String> catalogVersionKeys;
}
