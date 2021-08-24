package nl.siegmann.kingfisher.graphql.domain.contentitem;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LocalizedStringFieldValue extends FieldValue {

	private String content;
	
	private final List<LocalizedString> contents = new ArrayList<>();
}
