package nl.siegmann.kingfisher.cms.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = {"key", "content_type_id"})
})
public class FieldDefinition {

	@Id
	@GeneratedValue
	private Long id;

	private String key;

	private String name;
	
	private String description;
	
	@ManyToOne
	private ContentType contentType;

	@NotNull
	@Enumerated(EnumType.STRING)
	private FieldType fieldType;

	/**
	 * Order of the definition with the contentType
	 */
	private Integer sequence;
}
