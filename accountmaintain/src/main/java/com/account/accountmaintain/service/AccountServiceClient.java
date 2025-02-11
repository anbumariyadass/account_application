package com.account.accountmaintain.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.account.accountmaintain.dto.UserResponseDTO;

@Service
public class AccountServiceClient {
	@Autowired
	private RestTemplate restTemplate;
	
	public UserDetails getUserDetails(String username) {
	    String url = "http://localhost:8081/auth/userdetail/" + username;
	    
	    UserResponseDTO userDTO = restTemplate.getForObject(url, UserResponseDTO.class);
	    
	    if (userDTO == null) {
	    	throw new UsernameNotFoundException("User not found: " + username);
        }

        // Convert UserResponseDTO to UserDetails (Spring Security User)
        
        return User.withUsername(userDTO.getUsername())
                .password("") // Password should be empty for security
                .authorities(userDTO.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // ✅ Convert String to SimpleGrantedAuthority
                        .collect(Collectors.toList()))
                .build();
	}

}
