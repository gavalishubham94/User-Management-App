package com.bikkadIt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikkadIt.entities.UserAccounts;

public interface UserAccountRepo extends JpaRepository<UserAccounts, Integer> {

}
