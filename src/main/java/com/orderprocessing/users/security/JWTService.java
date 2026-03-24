package com.orderprocessing.users.security;

import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.orderprocessing.common.filters.JWTValidator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
/**
 * Bean that generates tokens (with roles claim embedded), validates tokens and extracts token info.
 * Holds secret key and expiration defined in application.properties.
 */
public class JWTService implements JWTValidator {

	private final SecretKey secretKey;

	// represented in ms
	private final long expiration;

	public JWTService(@Value("${jwt.secret}") String secret,
			@Value("${jwt.expiration}") long expiration) {
		this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
		this.expiration = expiration;
	}

	public String generateToken(AuthenticatedUser authenticatedUser) {
		final Date created = new Date();
		final List<String> roles = authenticatedUser.getAuthorities().stream()
	            .map(GrantedAuthority::getAuthority)
	            .toList();
		return Jwts.builder()
			.subject(authenticatedUser.getUsername())
			.claim("roles", roles) //$NON-NLS-1$
			.claim("externalId", authenticatedUser.getExternalId()) //$NON-NLS-1$
			.issuedAt(created)
			.expiration(new Date(created.getTime() + expiration))
			.signWith(secretKey)
			.compact();
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final boolean expired = isTokenExpired(token);
		if (expired) {
			return false;
		}
		final String username = getUsername(token);
		return username.equals(userDetails.getUsername());
	}

	@Override
	public String getUsername(String token) {
		final Claims claims = extractClaims(token);
		return claims.getSubject();
	}

	@Override
	public List<String> getRoles(String token) {
	    final Claims claims = extractClaims(token);
	    final List<?> roles = claims.get("roles", List.class); //$NON-NLS-1$
	    return roles.stream()
	    			.filter(String.class::isInstance)
	    			.map(String.class::cast)
	    			.toList();
	}

	private boolean isTokenExpired(String token) {
		final Claims claims = extractClaims(token);
		return claims.getExpiration().before(new Date());
	}

	private Claims extractClaims(String token) {
		return Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
}
