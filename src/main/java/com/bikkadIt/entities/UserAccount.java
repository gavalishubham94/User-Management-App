package com.bikkadIt.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "User_Accounts")
@Data
public class UserAccount {

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
	
	@Column(name = "USER_PASSWORD")
	private String userPassword;
	
	@Column(name = "USER_PHNO")
	private String userPhNo;
	
	@Column(name = "USER_DOB")
	private String DOB;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "COUNTRY_ID")
	private Integer countryId;
	
	@Column(name = "STATE_ID")
	private Integer stateId;
	
	@Column(name = "CITY_ID")
	private Integer cityId;
	
	@Column(name = "ACCOUNT_STATUS")
	private String accountStatus;
	
	@CreationTimestamp
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name = "UPDATED_DATE", updatable = true, insertable = false)
	private Date updatedDate;
	
}
