package com.bikkadIt.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadIt.bindings.LoginForm;
import com.bikkadIt.bindings.UserRegistrationForm;

import com.bikkadIt.service.UserServiceI;

@RestController
public class LoginController {

	@Autowired
	private UserServiceI userServiceI;
	
	
	
	@GetMapping("/login")
	public ResponseEntity<String> saveUser(@RequestBody LoginForm loginForm) {
		
		String message = userServiceI.loginCheck(loginForm);
	
		
		return new ResponseEntity<String>(message,HttpStatus.OK)   ;}
	
}
