package com.bikkadIt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikkadIt.entities.UserAccount;

@Repository
public interface UserAccountRepo extends JpaRepository<UserAccount, Integer> {

	  public UserAccount findByUserEmailAndUserPassword(String userEmail,String userPassword);
}
