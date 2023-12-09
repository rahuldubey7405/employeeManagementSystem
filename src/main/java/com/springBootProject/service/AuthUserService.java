package com.springBootProject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springBootProject.model.AuthUser;
import com.springBootProject.repository.AuthUserRepository;

@Service
public class AuthUserService implements UserDetailsService {
	@Autowired
	private AuthUserRepository userRepository;

	public AuthUser saveAuthUser(AuthUser authUser) {
		return userRepository.save(authUser);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<AuthUser> userDetail = Optional.of(userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found" + username)));
		return userDetail.get();
//		try {
//			Optional<AuthUser> userDetail = userRepository.findByEmail(username);
//			return userDetail.get();
//		} catch (UsernameNotFoundException e) {
//			return User;
//		}

		// Converting userDetail to UserDetails
//		return userDetail.map(AuthUser::new)
//				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
//		return null;
	}

}
