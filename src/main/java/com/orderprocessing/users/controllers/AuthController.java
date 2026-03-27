package com.orderprocessing.users.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderprocessing.users.constants.Constants;
import com.orderprocessing.users.security.AuthError;
import com.orderprocessing.users.security.AuthJWTService;
import com.orderprocessing.users.security.AuthRequest;
import com.orderprocessing.users.security.AuthResponse;
import com.orderprocessing.users.security.AuthenticatedUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Authentication controller", description = "Provides a JWT for valid credentials")
@RestController
public class AuthController {

	private final AuthenticationManager authManager;
	private final AuthJWTService jwtService;

	public AuthController(AuthenticationManager authManager, AuthJWTService jwtService) {
		this.authManager = authManager;
		this.jwtService = jwtService;
	}

	/**
	 * This method is for OpenAPI documentation purposes.
	 * The real authentication takes place in {@link com.orderprocessing.users.security.JsonLoginFilter.doFilter()}
	 */
	@Operation(summary = "Authentication endpoint", description = "Authenticates the provided credentials and provides a JWT")
	@ApiResponse(responseCode = "200",
				description = "Successful authentication",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AuthResponse.class)))
	@ApiResponse(responseCode = "401",
				description = "Incorrect credentials",
				content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AuthError.class)))
	@PostMapping(value = Constants.LOGIN_PATH)
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
		final Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		final AuthenticatedUser details = (AuthenticatedUser) authentication.getPrincipal();
		// generate authenticated token
		final String token = jwtService.generateToken(details);
		return ResponseEntity.ok(new AuthResponse(token));
	}
}
