package com.sblog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sblog.dto.AuthenticationResponse;
import com.sblog.dto.LoginRequest;
import com.sblog.dto.RegisterRequest;
import com.sblog.model.User;
import com.sblog.repository.UserRepository;
import com.sblog.security.JwtProvider;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	 public void signup(RegisterRequest registerRequest) {
	        User user = new User();
	        user.setUserName(registerRequest.getUsername());
	        user.setEmail(registerRequest.getEmail());
	        user.setPassword(encodePassword(registerRequest.getPassword()));

	        userRepository.save(user);
	        
	         }

	private String encodePassword(String password) {
		
		return passwordEncoder.encode(password);
	}

	public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername());
    }

	public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }

}
