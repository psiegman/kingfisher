package nl.siegmann.kingfisher.cms.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.util.CollectionUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "key", "catalog_version_id" }) })
public class ContentItem extends AbstractDomainObject {

	private String key;

    private String name;

	private String description;
	
	@Column(name = "catalog_version_id", insertable = false, updatable = false)
	private UUID catalogVersionId;
		
	@JoinColumn(name = "catalog_version_id")
	@ManyToOne(optional = false)
	private CatalogVersion catalogVersion;

	@Column(name = "content_type_id", insertable = false, updatable = false)
	private UUID contentTypeId;

	@JoinColumn(name = "content_type_id")
	@ManyToOne(optional = false)
	private ContentType contentType;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "contentItem")
	private List<Field> fields;
	
	public Optional<Field> getFieldByKey(String fieldKey) {
		if (CollectionUtils.isEmpty(fields)) {
			return Optional.<Field>empty();
		}
		return fields.stream().filter(field -> field.getFieldDefinition().getKey().equals(fieldKey)).findAny();
	}
}
