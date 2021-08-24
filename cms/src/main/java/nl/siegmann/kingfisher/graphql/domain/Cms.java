package nl.siegmann.kingfisher.graphql.domain;

import lombok.Builder;
import lombok.Data;
import nl.siegmann.kingfisher.cms.domain.UserContext;

@Data
@Builder
public class Cms {

	private UserContext userContext;
	private CmsContent cmsContent;
}
