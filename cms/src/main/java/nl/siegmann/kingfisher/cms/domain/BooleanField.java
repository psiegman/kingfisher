package nl.siegmann.kingfisher.cms.domain;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class BooleanField extends Field {

	private Boolean value;

}
