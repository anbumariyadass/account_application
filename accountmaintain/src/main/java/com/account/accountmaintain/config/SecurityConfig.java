package com.account.accountmaintain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.account.accountmaintain.jwt.JwtFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable()) // Disable CSRF for REST APIs
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/account/publicendpoint").permitAll() // Public endpoints
	                //.requestMatchers("/auth/allusers").hasAuthority("ROLE_ADMIN") // Role-based restriction
	                //.requestMatchers("/users/allusers").hasAnyAuthority("ROLE_ADMIN", "ROLE_ACCOUNTANT") // 🔥 Role-based restriction
	                //.requestMatchers("/users/allusers").hasAnyRole("ADMIN", "ACCOUNTANT") // Automatically prefixes "ROLE_"
	                .anyRequest().authenticated() // All other endpoints require authentication
	            		//.anyRequest().permitAll()
	            )
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Ensures no session is stored
	            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // JWT authentication

	        return http.build();
	    }
}

