package com.bikkadIt.bindings;

import lombok.Data;

@Data
public class UserRegistrationForm {

	
    private Integer userId;
	
	private String userFirstName;
	
	private String userLastName;
	
	private String userEmail;
	
	private String userPassword;

	private String userPhNo;

	private String DOB;

	private String gender;

	private Integer countryId;

	private Integer stateId;

	private Integer cityId;

	private String accountStatus;
}
