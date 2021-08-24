package nl.siegmann.kingfisher.api.json;

import java.io.IOException;
import java.util.Locale;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import nl.siegmann.kingfisher.api.domain.LocalizedStringFieldValue;

public class LocalizedStringValueSerializer extends StdSerializer<LocalizedStringFieldValue> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4473144346849058098L;

	public LocalizedStringValueSerializer() {
		this(LocalizedStringFieldValue.class);
	}

	protected LocalizedStringValueSerializer(Class<LocalizedStringFieldValue> t) {
		super(t);
	}


	@Override
	public void serialize(LocalizedStringFieldValue fieldValue, JsonGenerator jgen, SerializerProvider provider)
			throws IOException {
		jgen.writeStartObject();
		for (Entry<Locale, String> localeValue : fieldValue.getContents().entrySet()) {
			jgen.writeStringField(localeValue.getKey().toString(), localeValue.getValue());
		}
		jgen.writeEndObject();
	}
}
