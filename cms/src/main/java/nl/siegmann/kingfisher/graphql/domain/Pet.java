package nl.siegmann.kingfisher.graphql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Pet {
	private String name;
}
