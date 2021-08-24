package nl.siegmann.kingfisher.cms.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "key", "catalog_id" }) })
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatalogVersion extends AbstractDomainObject {

	private String key;

	private String name;
	
	private String description;

	@ManyToOne(optional=false)
	private Catalog catalog;
}
