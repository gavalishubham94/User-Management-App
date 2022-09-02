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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_ACCOUNTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccounts {

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
	private String UserPassword;
	
	@Column(name = "USER_PHNO")
	private String userPhNo;
	
	@Column(name = "USER_DOB")
	private String DOB;
	
	@Column(name = "GENDER")
	private String Gender;
	
	@Column(name = "COUNTRY_ID")
	private Integer CountryId;
	
	@Column(name = "STATE_ID")
	private Integer StateId;
	
	@Column(name = "CITY_ID")
	private Integer CityId;
	
	@Column(name = "ACCOUNT_STATUS")
	private char AccountStatus;
	
	@CreationTimestamp
	@Column(name = "CREATED_DATE", updatable = false)
	private Date CreatedDate;
	
	@UpdateTimestamp
	@Column(name = "UPDATED_DATE", updatable = true, insertable = false)
	private Date UpdatedDate;
	
	
}
