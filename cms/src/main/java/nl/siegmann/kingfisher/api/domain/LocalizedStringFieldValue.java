package nl.siegmann.kingfisher.api.domain;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.siegmann.kingfisher.api.json.LocalizedStringValueSerializer;

@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonSerialize(using = LocalizedStringValueSerializer.class)
public class LocalizedStringFieldValue extends FieldValue {

	private final Map<Locale, String> contents = new HashMap<>();
}
