package com.bikkadIt.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_ACCOUNTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User_Accounts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "USER_FIRST_NAME")
	private String userFirstName;
	
	@Column(name = "USER_LAST_NAME")
	private String userLastName;
	
	@Column(name = "USER_EMAIL")
	private String userEmail;
	
	@Column(name = "USER_PHNO")
	private String userPhNo;
	
	@Column(name = "USER_DOB")
	private String DOB;
	
	@Column(name = "GENDER")
	private String Gender;
	
	@Column(name = "COUNTRY")
	private String Country;
	
	@Column(name = "STATE")
	private String State;
	
	@Column(name = "CITY")
	private String City;
	
	@Column(name = "ACTIVE_SW")
	private char ActiveSw;
	
	@Column(name = "CREATED_DATE")
	private Date CreatedDate;
	
	@Column(name = "UPDATED_DATE")
	private Date UpdatedDate;
	
	@Column(name = "USER_PASSWORD")
	private String UserPassword;
}
