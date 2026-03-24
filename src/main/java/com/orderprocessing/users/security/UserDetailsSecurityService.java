package com.orderprocessing.users.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.orderprocessing.users.constants.UserStatus;
import com.orderprocessing.users.repositories.CustomerUserRepository;

import jakarta.transaction.Transactional;

@Service
/**
 * Helper bean that loads the user details from the database based on the user identifier.
 */
public class UserDetailsSecurityService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final CustomerUserRepository repository;

	public UserDetailsSecurityService(CustomerUserRepository repository) {
		this.repository = repository;
	}

	@Transactional
	@Override
	public AuthenticatedUser loadUserByUsername(String identifier) throws UsernameNotFoundException {
		final var dbUser = repository.findByUsername(identifier)
							.orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", identifier))); //$NON-NLS-1$
		final var role = new SimpleGrantedAuthority(dbUser.getRole().getRole());
		return new AuthenticatedUser(dbUser.getExternalId(),
							dbUser.getUsername(),
							dbUser.getPassword(),
							UserStatus.ACTIVE.equals(dbUser.getStatus().getStatus()),
							List.of(role));
	}

}
