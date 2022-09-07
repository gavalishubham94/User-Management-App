package com.bikkadIt.service;

import java.util.Map;

import com.bikkadIt.bindings.LoginForm;


public interface UserServiceI {

	public String loginCheck(LoginForm loginForm);
	
	public Map<Integer, String> getCountries();

	public Map<Integer, String> getStatesById(Integer countryId);
	
	public Map<Integer, String> getCitiesById(Integer stateId);
}
