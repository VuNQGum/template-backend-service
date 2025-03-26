
package com.evnit.ttpm.stdv.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.evnit.ttpm.stdv.model.CustomUserDetails;
import com.evnit.ttpm.stdv.model.User;
import com.evnit.ttpm.stdv.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> dbUser = userRepository.findByUsername(username);
		return dbUser.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(
				"Couldn't find a matching user email in the database for " + username));
	}

	public UserDetails loadUserById(String username) {
		Optional<User> dbUser = userRepository.findByUsername(username);
		return dbUser.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(
				"Couldn't find a matching user id in the database for " + username));
	}
}
