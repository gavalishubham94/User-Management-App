package com.bikkadIt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikkadIt.entities.StatesMaster;


public interface StateRepository extends JpaRepository<StatesMaster, Integer>{

}
