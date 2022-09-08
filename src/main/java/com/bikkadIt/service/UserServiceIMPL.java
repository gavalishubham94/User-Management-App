package com.bikkadIt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikkadIt.bindings.LoginForm;
import com.bikkadIt.bindings.UnlockAccountForm;
import com.bikkadIt.bindings.UserRegistrationForm;
import com.bikkadIt.entities.CityMaster;
import com.bikkadIt.entities.CountryMaster;
import com.bikkadIt.entities.StateMaster;
import com.bikkadIt.entities.UserAccount;
import com.bikkadIt.repositories.CityRepo;
import com.bikkadIt.repositories.CountryRepo;
import com.bikkadIt.repositories.StateRepo;
import com.bikkadIt.repositories.UserAccountRepo;



@Service
public class UserServiceIMPL implements UserServiceI{

	@Autowired
	private UserAccountRepo userAccountRepo;
	
	@Autowired 
	private CountryRepo countryRepo;
	
	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private CityRepo cityRepo;
	
	
	@Override
	public String loginCheck(LoginForm loginForm) {
		
		UserAccount userAccounts = this.userAccountRepo.findByUserEmailAndUserPassword(loginForm.getEmail(), loginForm.getPwd());
		
		if(userAccounts!=null) {
			
			if(userAccounts.getAccountStatus().equals("LOCKED")) {
				
				return "your accoount is locked";
				
			}else {
				
				return "Logedin Successfully, welcome to BikkadIT";
				
			}
			
		}
		
		return "Invalid credentials";
	}

	@Override
	public Map<Integer, String> getCountries() {

		List<CountryMaster> countries = this.countryRepo.findAll();
		
		Map<Integer, String> map=new HashMap<>();
		
		for(CountryMaster country:  countries) {
			
			map.put(country.getCountryId(), country.getCountryName());
		}
	

		return map;
	}
	
	
	@Override
	public Map<Integer, String> getStatesById(Integer countryId) {

		List<StateMaster> states = this.stateRepo.findAllByCountryId(countryId);
		
		Map<Integer, String> map=new HashMap<>();
		
		for(StateMaster state:  states){
			
			map.put(state.getStateId(),state.getStateName());
		}
	

		return map;
	}

	@Override
	public Map<Integer, String> getCitiesById(Integer stateId) {
		List<CityMaster> cities = this.cityRepo.findAllByStateId(stateId);
		
		Map<Integer, String> map=new HashMap<>();
		
		for(CityMaster city  :cities) {
			map.put(city.getCityId(), city.getCityName());
		}
		
		return map;
	}

	@Override
	public boolean saveUser(UserRegistrationForm userRegistrationForm) {

		userRegistrationForm.setAccountStatus("LOCKED");
		userRegistrationForm.setUserPassword(autoGeneratePassword());
		
		UserAccount userAccount=new UserAccount();
		
		BeanUtils.copyProperties(userRegistrationForm, userAccount);
		
		UserAccount savedUser = userAccountRepo.save(userAccount);
		
		if(savedUser!=null){
			return true;
		}
		
		return false;
	}
	
	private String autoGeneratePassword() {
		
		String generatedPassword = RandomStringUtils.randomAlphanumeric(6);
		
		return generatedPassword;
	}

	@Override
	public boolean unlockAccount(UnlockAccountForm unlockAccountForm) {
		
		String email = unlockAccountForm.getEmail();
		String tempPassword = unlockAccountForm.getTempPassword();
		
		UserAccount user = this.userAccountRepo.findByUserEmailAndUserPassword(email, tempPassword);
		
		if(user!=null) {
			
			user.setAccountStatus("UNLOCKED");
			user.setUserPassword(unlockAccountForm.getNewPassword());
			
			UserAccount save = this.userAccountRepo.save(user);
			
			
			return true;
		}
		
		return false;
	}

	@Override
	public String forgetPassword(String email) {

		UserAccount user = this.userAccountRepo.findByUserEmail(email);

		if(user!=null) {
			
			return "Success";
		}
		
		return "Failed";
	}
	

}
