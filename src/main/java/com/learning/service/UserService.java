package com.learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.entity.UserEntity;
import com.learning.repo.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsernameAndIsActive(username, true)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = findByUsername(username);
		
		return User.builder().username(user.getUsername()).password(user.getPassword()).build();
	}

}
