package com.orderprocessing.users.security;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.orderprocessing.common.constants.Constants;
import com.orderprocessing.common.filters.JWTValidator;
import com.orderprocessing.common.filters.JWTValidatorCommon;

import io.jsonwebtoken.Jwts;

@Service
/**
 * Bean that generates tokens (with roles claim and user external id embedded) and extracts token information.
 * Holds secret key and expiration defined in application.properties.
 */
public class AuthJWTService extends JWTValidatorCommon implements JWTValidator {

	// represented in ms
	private final long expiration;

	public AuthJWTService(@Value("${jwt.secret}") String secret,
			@Value("${jwt.expiration}") long expiration) {
		super(secret);
		this.expiration = expiration;
	}

	public String generateToken(AuthenticatedUser authenticatedUser) {
		final Date created = new Date();
		final List<String> roles = authenticatedUser.getAuthorities().stream()
	            .map(GrantedAuthority::getAuthority)
	            .toList();
		return Jwts.builder()
			.subject(authenticatedUser.getUsername())
			.claim(Constants.ROLES, roles)
			.claim(Constants.PARAM_EXTERNAL_ID, authenticatedUser.getExternalId())
			.issuedAt(created)
			.expiration(new Date(created.getTime() + expiration))
			.signWith(getSecret())
			.compact();
	}

}
