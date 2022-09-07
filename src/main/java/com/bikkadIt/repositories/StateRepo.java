package com.bikkadIt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikkadIt.entities.StateMaster;

@Repository
public interface StateRepo extends JpaRepository<StateMaster, Integer> {

     public List<StateMaster>  	findAllByCountryId(Integer countryId);
}
