package nl.siegmann.kingfisher.graphql.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Dog extends Pet {
	private String bark;

	@Builder
	public Dog(String name,String bark) {
		super(name);
		this.bark = bark;
	}
}
