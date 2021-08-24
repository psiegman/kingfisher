package nl.siegmann.kingfisher.api.domain.response;

public abstract class SingleResponse<T> {

	private T value;
	
	public T getValue() {
		return value;
	}
	
	
}
