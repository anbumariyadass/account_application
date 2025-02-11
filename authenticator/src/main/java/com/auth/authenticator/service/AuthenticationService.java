package com.auth.authenticator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.authenticator.entity.User;
import com.auth.authenticator.repository.UserRepository;
import com.auth.authenticator.security.JwtUtil;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    //@Autowired
    //private AuthenticationManager authenticationManager;
    
    public String encodePassword(String password) {
        return passwordEncoder.encode(password); // Hash the password
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword); // Verify password
    }
    
    public String register(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        userRepository.save(user);
        return "User Registered Successfully";
    }
    
    public String authenticate(String username, String password) {
       // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User userDetails = userRepository.findByUsername(username).orElseThrow();
        if (verifyPassword(password, userDetails.getPassword())) {
        	return jwtUtil.generateToken(userDetails.getUsername());
        } else {
        	return "Invalid Access";
        }
    }
    
    public List<User> getAllUsers() {
         return userRepository.findAll();
    }
    
    public User getUser(String userName) {
        return userRepository.findByUsername(userName).orElse(null);
   }
    
    public UserDetails getUserDetails(String userName) {
    	return userDetailsService.loadUserByUsername(userName);
    }
}
