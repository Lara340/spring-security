package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.model.AuthRequest;
import com.learning.service.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/generate-token")
	public String generateToken(@RequestBody AuthRequest authRequest) {
	  
		Authentication authentication	= authenticationManager.authenticate(
			  new UsernamePasswordAuthenticationToken( authRequest.getUsername(), authRequest.getPassword()));
	  
	  if (authentication.isAuthenticated()) {
		  return jwtService.generateToken(authRequest.getUsername());
	  }
	  
	  return null;
	}

}
