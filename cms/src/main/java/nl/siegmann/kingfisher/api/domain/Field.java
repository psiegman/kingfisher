package nl.siegmann.kingfisher.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Field {
	private String key;
	private String type;
	
	@JsonProperty(value = "value")
	private FieldValue fieldValue;
}
