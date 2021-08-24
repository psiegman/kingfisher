package nl.siegmann.kingfisher.cms.domain;

public enum FieldType {

	/**
	 * A piece of text of max 255 characters long
	 */
	STRING,

	/**
	 * A piece of text of that may be very long (4 gig on mysql) and has a mimetype
	 */
	TEXT,

	/**
	 * A localized piece of text of max 255 characters long
	 */
	L_STRING
}
