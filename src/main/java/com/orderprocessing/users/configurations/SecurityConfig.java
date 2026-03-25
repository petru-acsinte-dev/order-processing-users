package com.orderprocessing.users.configurations;

import static com.orderprocessing.users.constants.Constants.LOGIN_PATH;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessing.common.constants.Constants;
import com.orderprocessing.common.filters.JWTFilter;
import com.orderprocessing.users.security.AuthJWTService;
import com.orderprocessing.users.security.JsonLoginFilter;

@Configuration
@EnableWebSecurity
/**
 * Configuration class that assembles the filter chain.
 * Allows /login/auth, Swagger UI and actuator endpoints (info and health).
 * Requires JWT authentication on everything else.
 * Adds {@link JsonLoginFilter} before {@link JWTFilter} in the filter chain.
 */
public class SecurityConfig {

	private final UserDetailsService userDetails;
    private final AuthJWTService jwtService;
    private final ObjectMapper objectMapper;

    public SecurityConfig(UserDetailsService userDetails,
                          AuthJWTService jwtService,
                          ObjectMapper objectMapper) {
        this.userDetails = userDetails;
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
    }

    @Bean
    JWTFilter jwtFilter(AuthJWTService jwtService) {
        return new JWTFilter(jwtService);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
    										AuthenticationManager authManager,
    										JWTFilter jwtFilter,
                                            DaoAuthenticationProvider authenticationProvider) throws Exception {

        // Define the filter beans
        final JsonLoginFilter jsonLoginFilter = new JsonLoginFilter(authManager, jwtService, objectMapper);

        http.csrf(CsrfConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(LOGIN_PATH,
                    				Constants.ACTUATOR_HEALTH,
                    				Constants.ACTUATOR_INFO,
                    				Constants.SWAGGER_UI,
                    				Constants.V3_API_DOCS
                                ).permitAll()
                    .anyRequest().authenticated()
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            // JWT filter protects all other endpoints
            .addFilterBefore(jwtFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
            // Add JSON login filter before JWT filter to avoid empty body
            .addFilterBefore(jsonLoginFilter, JWTFilter.class);

        return http.build();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetails);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}