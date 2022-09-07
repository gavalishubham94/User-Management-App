package com.bikkadIt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "City_master")
@Data
public class CityMaster {


	@Column(name = "CITY_ID")
	@Id
	private Integer cityId;
	
	@Column(name = "CITY_NAME")
	private String cityName;
	
	
	@Column(name = "STATE_ID")
	private Integer stateId;
}
