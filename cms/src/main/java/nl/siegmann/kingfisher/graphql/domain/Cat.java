package nl.siegmann.kingfisher.graphql.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Cat extends Pet {
	private String meow;

	@Builder
	public Cat(String name,String meow) {
		super(name);
		this.meow= meow;
	}
}
