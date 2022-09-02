package com.bikkadIt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikkadIt.entities.User_Accounts;

public interface UserRepo extends JpaRepository<User_Accounts, Integer> {

}
