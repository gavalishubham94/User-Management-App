package com.bikkadIt.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "country_master")
@Data
public class CountryMaster {

	@Column(name = "COUNTRY_ID")
	@Id
	private Integer countryId;
	
	
	@Column(name = "COUNTRY_NAME")
	private String countryName;
}
