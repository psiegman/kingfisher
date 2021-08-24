package nl.siegmann.kingfisher.graphql.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import graphql.schema.GraphQLType;
import graphql.servlet.GraphQLTypesProvider;

@Component
public class KingfisherGraphQLTypesProvider implements GraphQLTypesProvider{

	@Override
	public Collection<GraphQLType> getTypes() {
		List<GraphQLType> graphQLTypes = new ArrayList<>();
		graphQLTypes.add(new GraphQLType() {
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		return graphQLTypes;
	}

}
