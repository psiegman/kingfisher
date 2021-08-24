package nl.siegmann.kingfisher.graphql.domain.contentitem;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocalizedString {
	private String locale;
	private String value;
}
