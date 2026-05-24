package com.petromirdzhunev.users.app.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.petromirdzhunev.users.domain.AuthUser;
import com.petromirdzhunev.users.domain.repository.AuthUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

	private final AuthUserRepository authUserRepository;

	public AuthUser authenticatedUser() {
		return (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	public UserDetails userDetailsById(final Long userId) {
		return this.authUserRepository.authUser(userId);
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		return authUserRepository.authUser(username);
	}

}
