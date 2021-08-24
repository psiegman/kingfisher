package nl.siegmann.kingfisher.graphql.domain.contenttype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteContentTypePayload {
	private ContentType contentType;
}
