package com.bikkadIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikkadIt.entities.UserAccounts;
import com.bikkadIt.repositories.UserAccountRepo;

@Service
public class UserServiceIMPL implements UserServiceI{

	@Autowired
	private UserAccountRepo userAccountRepo;
	
	@Override
	public UserAccounts SaveUser(UserAccounts userAccounts) {

		UserAccounts savedUser = this.userAccountRepo.save(userAccounts);

		return savedUser;
	}

}
