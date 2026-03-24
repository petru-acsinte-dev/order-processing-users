package com.orderprocessing.users.security;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Identifies an existing order processing user.
 */
public class AuthenticatedUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final UUID externalId;
	private final String username;
	private final String password;
	private final boolean enabled;
	private final Collection<GrantedAuthority> authorities;

	public AuthenticatedUser(UUID externalId,
							String username,
							String password,
							boolean enabled,
							Collection<GrantedAuthority> authorities) {
		this.externalId = externalId;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public UUID getExternalId() {
		return externalId;
	}

}
