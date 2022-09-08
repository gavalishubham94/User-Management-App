package com.bikkadIt.bindings;

import lombok.Data;

@Data
public class UnlockAccountForm {

	private String Email;
	
	private String TempPassword;
	
	private String NewPassword;
	
	private String ConfirmPasswword;

	
}
