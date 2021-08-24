package nl.siegmann.kingfisher.graphql.domain.contenttype;

import lombok.Getter;

@Getter
public enum FieldType {

	/**
	 * A piece of text of max 255 characters long
	 */
	STRING("string"),

	/**
	 * A piece of text of that may be very long (4 gig on mysql) and has a mimetype
	 */
	TEXT("text"),


	/**
	 * A localized piece of text of max 255 characters long
	 */
	ISTRING("istring");
	
	private final String key;
	
	private FieldType(String key) {
		this.key = key;
	}
}
