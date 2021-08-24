package nl.siegmann.kingfisher.cms.domain;

import java.util.Locale;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserContext {

	private Locale locale = Locale.US;
}
