package nl.siegmann.kingfisher.graphql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coxautodev.graphql.tools.SchemaParserDictionary;

import nl.siegmann.kingfisher.graphql.domain.Cat;
import nl.siegmann.kingfisher.graphql.domain.Dog;
import nl.siegmann.kingfisher.graphql.domain.contentitem.LocalizedStringFieldValue;
import nl.siegmann.kingfisher.graphql.domain.contentitem.StringFieldValue;
import nl.siegmann.kingfisher.graphql.domain.contentitem.TextFieldValue;

@Configuration
public class GraphQLConfig {

	@Bean
	public SchemaParserDictionary schemaParserDictionary() {
		SchemaParserDictionary schemaParserDictionary = new SchemaParserDictionary();
		addToSchemaParserDictionary(schemaParserDictionary, StringFieldValue.class);
		addToSchemaParserDictionary(schemaParserDictionary, LocalizedStringFieldValue.class);
		addToSchemaParserDictionary(schemaParserDictionary, TextFieldValue.class);
		addToSchemaParserDictionary(schemaParserDictionary, Cat.class);
		addToSchemaParserDictionary(schemaParserDictionary, Dog.class);
		return schemaParserDictionary;
	}

	private void addToSchemaParserDictionary(SchemaParserDictionary schemaParserDictionary, Class<?> aClass) {
		schemaParserDictionary.add(aClass.getSimpleName(), aClass);
	}
}
