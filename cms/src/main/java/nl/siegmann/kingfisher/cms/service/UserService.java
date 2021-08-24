package nl.siegmann.kingfisher.cms.service;

import java.util.Locale;

import org.springframework.stereotype.Service;

import nl.siegmann.kingfisher.cms.domain.UserContext;

@Service
public class UserService {

	private static final Locale DEFAULT_LOCALE = Locale.US;
	
	public UserContext getCurrentUserContext() {
		return UserContext.builder().locale(DEFAULT_LOCALE).build();
	}
}
