package nl.siegmann.kingfisher.cms.domain;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapKeyColumn;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
//@NoArgsConstructor
//@AllArgsConstructor
public class LocalizedStringField extends Field {

    @MapKeyColumn(name = "locale")
    @Column(name = "value")
    @CollectionTable(name = "l_string_field_value")
    @ElementCollection(fetch = FetchType.EAGER)
	private final Map<Locale, String> values = new HashMap<>();
}
