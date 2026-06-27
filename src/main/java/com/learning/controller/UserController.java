package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.UserEntity;
import com.learning.repo.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/encodePassword")
	public void saveUserWithEncodedPassword(@RequestParam String username, 
			                               @RequestParam String password) {
		UserEntity user = new UserEntity();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setActive(true);
		
		userRepository.save(user);
	}
	

}
