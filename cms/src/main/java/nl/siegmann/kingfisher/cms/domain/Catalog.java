package nl.siegmann.kingfisher.cms.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "content_catalog", uniqueConstraints = { @UniqueConstraint(columnNames = { "key", "schema_id" }) })
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Catalog extends AbstractDomainObject {

	private String key;

	private String name;

	private String description;

	@ManyToOne(optional = false)
	private Schema schema;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "catalog")
	private List<CatalogVersion> catalogVersions;
}
