package com.bikkadIt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadIt.service.UserServiceI;

@RestController
public class ForgotPasswordController {

	@Autowired
	private UserServiceI userServiceI;
	
	@GetMapping("/forgotpassword/{email}")
	public ResponseEntity<String> forgotPassword(@PathVariable String email){
		
		return new ResponseEntity<String> (this.userServiceI.forgetPassword(email),HttpStatus.OK);
		
	}
}
