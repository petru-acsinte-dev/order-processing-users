package com.orderprocessing.users.security;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessing.users.constants.Constants;

import io.swagger.v3.oas.models.PathItem.HttpMethod;
import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Intercepts POST requests against /login/auth endpoint.
 * Authenticates credentials via AuthenticationManager, then generates and returns JWT token on success.
 */
public class JsonLoginFilter extends GenericFilter {

	private static final long serialVersionUID = 1L;

	private final transient AuthenticationManager authManager;
    private final transient AuthJWTService jwtService;
    private final ObjectMapper objectMapper;

    public JsonLoginFilter(AuthenticationManager authManager, AuthJWTService jwtService, ObjectMapper objectMapper) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getServletPath();
        if (null == path || path.isBlank()) {
        	path = req.getRequestURI();
        }

        if (Constants.LOGIN_PATH.equals(path) && HttpMethod.POST.name().equalsIgnoreCase(req.getMethod())) {

        	try (InputStream inStream = req.getInputStream();
        		PrintWriter writer = resp.getWriter()) {
        		final AuthRequest authRequest = objectMapper.readValue(inStream, AuthRequest.class);

        		try {
	        		final Authentication authentication = authManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

	        		final String token = jwtService.generateToken((AuthenticatedUser) authentication.getPrincipal());

	        		resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
	        		writer.write(String.format("{\"token\":\"%s\"}", token)); //$NON-NLS-1$
	        		return; // do not continue filter chain for login
        		} catch (final Exception ex) {
        			resp.setContentType(MediaType.APPLICATION_JSON_VALUE);
        			resp.setStatus(HttpStatus.UNAUTHORIZED.value());
        			final AuthError error = new AuthError(HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage());
        			writer.write(error.toString());
        			return;
        		}
        	}
        }

        chain.doFilter(request, response);
    }
}