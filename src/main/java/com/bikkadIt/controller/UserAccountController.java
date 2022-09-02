package com.bikkadIt.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadIt.bindings.UserRegistrationForm;
import com.bikkadIt.entities.UserAccounts;
import com.bikkadIt.service.UserServiceI;

@RestController
public class UserAccountController {

	private UserServiceI userServiceI;
	
	@PostMapping("/saveUser")
	public UserAccounts saveUser(@RequestBody UserAccounts userAccounts) {
		
		UserAccounts savedUser = this.userServiceI.SaveUser(userAccounts);
	
		
		return savedUser;}
	
}
