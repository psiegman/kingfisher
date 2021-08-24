package nl.siegmann.kingfisher.cms.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Field {

	@Id
	@GeneratedValue
	private Long id;

	@Version
	private int version;

	@ManyToOne(optional=false)
	private FieldDefinition fieldDefinition;

	@ManyToOne(optional=false)
	private ContentItem contentItem;
	
}
