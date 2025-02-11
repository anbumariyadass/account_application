package com.auth.authenticator.contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.authenticator.dto.AuthRequest;
import com.auth.authenticator.dto.UserResponseDTO;
import com.auth.authenticator.entity.User;
import com.auth.authenticator.service.AuthenticationService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationService authenticationService;
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return ResponseEntity.ok(authenticationService.register(user));
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        String token = authenticationService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(token);
    }
    
    //@PreAuthorize("hasRole('ADMIN')")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/allusers")
    public ResponseEntity<List<User>> getAllUsers() {
    	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	    System.out.println("Current User: " + auth.getName());
    	    System.out.println("Authorities: " + auth.getAuthorities());
    	List<User> allUsers = authenticationService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
    
    @GetMapping("/whoami")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_ACCOUNTANT')")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_ACCOUNTANT')")
    //@PreAuthorize("hasAnyRole('ADMIN', 'ACCOUNTANT')")
    public String whoAmI() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    System.out.println("Current User: " + auth.getName());
	    System.out.println("Authorities: " + auth.getAuthorities());
    	return "Hi "+auth.getName()+", Your role is "+auth.getAuthorities();
    }
    
    @GetMapping("/userdetail/{userName}")
    public ResponseEntity<UserResponseDTO> getUserDetails(@PathVariable String userName) {
    	User user = authenticationService.getUser(userName);
    	if (user == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    	}

    	// Convert User entity to DTO
    	
    	List<String> roles = new ArrayList<>();
    	roles.add(user.getRole());
    	UserResponseDTO userDTO = new UserResponseDTO(
    			user.getUsername(), 
    			roles
    	);

    	return ResponseEntity.ok(userDTO);
    	
    }
}
