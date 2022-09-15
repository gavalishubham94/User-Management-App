package com.bikkadIt.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.bikkadIt.props.AppConstant;
import com.bikkadIt.props.AppProps;
import com.bikkadIt.repositories.CityRepo;
import com.bikkadIt.repositories.CountryRepo;
import com.bikkadIt.repositories.StateRepo;
import com.bikkadIt.repositories.UserAccountRepo;
import com.bikkadIt.util.EmailUtils;



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
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private AppProps appProps;
	
	@Override
	public String loginCheck(LoginForm loginForm) {
		
		UserAccount userAccounts = this.userAccountRepo.findByUserEmailAndUserPassword(loginForm.getEmail(), loginForm.getPwd());
		Map<String,String> messages = appProps.getMessages();
		
		
		if(userAccounts!=null) {
			
			if(userAccounts.getAccountStatus().equals(AppConstant.ACC_STATUS_LOCKED)) {
				
				return AppConstant.ACCOUNT_LOCKED;
				
			}else {
				
				return AppConstant.LOGIN_SUCCESS;
				
			}
			
		}
		
		return AppConstant.INVALID_CREDINTIALS;
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

		userRegistrationForm.setAccountStatus(AppConstant.ACC_STATUS_LOCKED);
		userRegistrationForm.setUserPassword(autoGeneratePassword());
		
		UserAccount userAccount=new UserAccount();
		
		BeanUtils.copyProperties(userRegistrationForm, userAccount);
		
		UserAccount savedUser = userAccountRepo.save(userAccount);
		
		if(savedUser!=null){
			
			String subject=AppConstant.USER_REG_EMAIL_SUBJECT;
			String body=getUserRegEmailBody(userRegistrationForm);
			emailUtils.sendMail(userRegistrationForm.getUserEmail(), subject, body);
			return true;
		}
		
		return false;
	}
	
	
	private String getUserRegEmailBody(UserRegistrationForm userRegistrationForm) {
		
		StringBuffer sb=new StringBuffer();
		String fileName=AppConstant.UNLOCK_ACC_EMAIL_BODY;
		List<String> lines=new ArrayList<>();
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get(fileName));
			lines = br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		lines.forEach(line->{
			if(line.contains(AppConstant.USER_FNAME)) {
				
			line=line.replace(AppConstant.USER_FNAME,userRegistrationForm.getUserFirstName());
				
			}
			
			if(line.contains(AppConstant.USER_LNAME)) {
				
				line=line.replace(AppConstant.USER_LNAME,userRegistrationForm.getUserLastName());
					
				}
             if(line.contains(AppConstant.TEMP_PASS)) {
				
				line=line.replace(AppConstant.TEMP_PASS,userRegistrationForm.getUserPassword());
					
				} 
              
			sb.append(line);
		});
		
		//lines.toString()    //alternate way for sb
		
		return sb.toString();
		
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
			
			user.setAccountStatus(AppConstant.ACC_STATUS_UNLOCKED);
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
			String subject=AppConstant.PASS_REECOVER_EMAIL_BODY;
			String body=recoverUserPassword(user);
			emailUtils.sendMail(email, subject,body);
			return AppConstant.PASS_RECOVERED;
		}
		
		return AppConstant.PASS_NOT_RECOVERED;
	}
	
	private String recoverUserPassword(UserAccount userAccount) {
		StringBuffer sb= new StringBuffer();
		String fileName=AppConstant.RECOVER_ACC_EMAIL_BODY;
	
		List<String> lines =new ArrayList<>();
		
		try {
			BufferedReader br = Files.newBufferedReader(Paths.get(fileName));
			lines=br.lines().collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lines.forEach(line->

		{
			if(line.contains(AppConstant.USER_FNAME)) {
				line.replace(AppConstant.USER_FNAME, userAccount.getUserFirstName());
			}
			if(line.contains(AppConstant.USER_LNAME)) {
				line.replace(AppConstant.USER_LNAME, userAccount.getUserLastName());
			}
			
			if(line.contains(AppConstant.USER_EMAIL)) {
				line.replace(AppConstant.USER_EMAIL, userAccount.getUserEmail());
			}
			sb.append(line);
		}
		
				);

		return sb.toString();

	}
	

}
