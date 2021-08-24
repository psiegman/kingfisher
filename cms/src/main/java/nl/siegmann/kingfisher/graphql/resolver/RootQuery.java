package nl.siegmann.kingfisher.graphql.resolver;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import graphql.schema.DataFetchingEnvironment;
import nl.siegmann.kingfisher.cms.service.UserService;
import nl.siegmann.kingfisher.graphql.domain.Cat;
import nl.siegmann.kingfisher.graphql.domain.CmsContent;
import nl.siegmann.kingfisher.graphql.domain.Dog;
import nl.siegmann.kingfisher.graphql.domain.Cms;
import nl.siegmann.kingfisher.graphql.domain.Pet;

@Component
public class RootQuery implements GraphQLQueryResolver {

	@Autowired
	private UserService userService;

	public List<Pet> getPets() {
		return Arrays.asList(
				Cat.builder().name("fluffy").meow("meep").build(),
				Dog.builder().name("fido").bark("woof").build()
				);
	}
	
	
	@Transactional
	public Cms getCms(DataFetchingEnvironment dataFetchingEnvironment) {
		return Cms
				.builder()
				.userContext(userService.getCurrentUserContext())
				.cmsContent(CmsContent.builder().build())
				.build();
	}
}