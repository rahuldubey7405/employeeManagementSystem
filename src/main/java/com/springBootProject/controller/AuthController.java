package com.springBootProject.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springBootProject.model.AuthUser;
import com.springBootProject.model.Employee;
import com.springBootProject.security.config.JwtTokenHelper;
import com.springBootProject.service.AuthUserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
	@Autowired
	private AuthUserService authUserService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private PasswordEncoder passEncoder;

	@PostMapping(value = { "/signup" })
	public ResponseEntity authUser(@RequestBody AuthUser authUser, HttpServletRequest request) {
		authUser.setPassword(this.passEncoder.encode(authUser.getPassword()));
		AuthUser user = authUserService.saveAuthUser(authUser);
		return new ResponseEntity(user, HttpStatus.CREATED);// status code 201
	}

	@PostMapping(value = { "/login" })
	public ResponseEntity loginUser(@RequestBody AuthUser authUser, HttpServletRequest request) {
		this.doAuthenticate(authUser.getEmail(), authUser.getPassword());
		UserDetails userDetails = authUserService.loadUserByUsername(authUser.getEmail());
		String token = jwtTokenHelper.generateToken(authUser);
		return new ResponseEntity(token, HttpStatus.CREATED);// status code 201
	}

	private void doAuthenticate(String email, String password) {
		try {
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, password);
			Authentication authenticate = authenticationManager.authenticate(auth);
		} catch (Exception e) {
			throw new RuntimeException("UserName or passwoed invalid");
		}

	}
}
