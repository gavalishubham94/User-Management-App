package com.bikkadIt.service;

import java.util.Map;

import com.bikkadIt.bindings.LoginForm;
import com.bikkadIt.bindings.UnlockAccountForm;
import com.bikkadIt.bindings.UserRegistrationForm;


public interface UserServiceI {

	public String loginCheck(LoginForm loginForm);
	
	public Map<Integer, String> getCountries();

	public Map<Integer, String> getStatesById(Integer countryId);
	
	public Map<Integer, String> getCitiesById(Integer stateId);
	
	public  boolean saveUser(UserRegistrationForm userRegistrationForm);
	
	public boolean unlockAccount(UnlockAccountForm unlockAccountForm);
	
	public String forgetPassword(String email);
	
}
