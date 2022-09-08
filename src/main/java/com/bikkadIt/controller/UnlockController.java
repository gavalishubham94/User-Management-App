package com.bikkadIt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadIt.bindings.UnlockAccountForm;
import com.bikkadIt.service.UserServiceI;

@RestController
public class UnlockController {

	@Autowired
	private UserServiceI userServiceI;
	
	
	@PostMapping("/unlockAccount")
	public ResponseEntity<String>   unlockAccount(@RequestBody UnlockAccountForm unlockAccountForm) {
		
		boolean result = this.userServiceI.unlockAccount(unlockAccountForm);
		if(result) {
			
			return new ResponseEntity<String>  ("Account is Unlocked",HttpStatus.CREATED);
		}
		return new ResponseEntity<String>  ("Account is not Locked",HttpStatus.BAD_REQUEST);
	}
	
}
