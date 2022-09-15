package com.bikkadIt.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bikkadIt.bindings.UserRegistrationForm;
import com.bikkadIt.props.AppConstant;
import com.bikkadIt.service.UserServiceI;

@RestController
public class RegistrationController {

	@Autowired
	private UserServiceI userServiceI;
	
	@GetMapping("/countries")
	public Map<Integer, String> countries(){
		
		Map<Integer, String> countries = this.userServiceI.getCountries();
		return countries;
	}
	
	@GetMapping("/states/{countryId}")
	public Map<Integer, String> states(@PathVariable Integer countryId){
		
		Map<Integer, String> states = this.userServiceI.getStatesById(countryId);
		return states;
	}
	
	@GetMapping("/cities/{stateId}")
	public Map<Integer,  String> cities(@PathVariable Integer stateId){
		
		Map<Integer, String> cities = this.userServiceI.getCitiesById(stateId);
		
		return cities;
		
	}
	
	
	@PostMapping("/registration")
	public ResponseEntity<String> registerUser(@RequestBody UserRegistrationForm userRegistrationForm){
		
		boolean result = this.userServiceI.saveUser(userRegistrationForm);
		
		if(result) {
			
			return new ResponseEntity<String>(AppConstant.REG_SUCCESS,HttpStatus.CREATED);
		}
		return new ResponseEntity<String>(AppConstant.REG_FAILED,HttpStatus.BAD_REQUEST);
	}
	
}












