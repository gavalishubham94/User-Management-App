package com.bikkadIt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikkadIt.entities.CountryMaster;


public interface CountryRepository extends JpaRepository<CountryMaster, Integer>{

}
