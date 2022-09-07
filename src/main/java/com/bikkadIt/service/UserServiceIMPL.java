package com.bikkadIt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikkadIt.bindings.LoginForm;
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
	

}
