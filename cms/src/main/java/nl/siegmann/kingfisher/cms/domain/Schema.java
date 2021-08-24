package nl.siegmann.kingfisher.cms.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cms_schema", uniqueConstraints = { @UniqueConstraint(columnNames = { "key" }),
		@UniqueConstraint(columnNames = { "name" }) })
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schema extends AbstractDomainObject {

//	 @Id
//	 @GeneratedValue
//	 private Long id;

//	@Id
//	@GeneratedValue(generator = "UUID")
//	@GenericGenerator(
//		name = "UUID",
//		strategy = "org.hibernate.id.UUIDGenerator"
//	)
//	@Column(name = "id", updatable = false, nullable = false)
//	private UUID id;

	private String key;

	private String name;

	private String description;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "schema")
	private List<Catalog> catalogs;
}
