package nl.siegmann.kingfisher.api.domain.response;

import java.util.List;

public abstract class ManyResponse<T> {

	private List<T> values;
	
	List<T> getValues() {
		return values;
	}

	ManyResponse<T> setValues(List<T> values) {
		this.values = values;
		return this;
	}
}
