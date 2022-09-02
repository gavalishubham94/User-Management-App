package com.bikkadIt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bikkadIt.entities.CitiesMaster;

public interface CityRepository extends JpaRepository<CitiesMaster, Integer>{

}
