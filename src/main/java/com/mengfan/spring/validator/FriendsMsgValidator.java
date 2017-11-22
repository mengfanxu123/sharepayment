package com.mengfan.spring.validator;

import java.io.IOException;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.mengfan.spring.pojo.FriendsMsg;


public class FriendsMsgValidator implements Validator {
	public boolean supports(Class aClass) {
		return aClass.equals(FriendsMsg.class);
	}
	
	public void validate(Object obj, Errors errors) {
		FriendsMsg fm = (FriendsMsg) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "requestName", "error.invalid.fm", "friends Name Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "error.invalid.bankAccount", "amount Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "validate", "error.invalid.bankAccount", "vaidate Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.bankAccount", "lastName Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "csv", "error.invalid.bankAccount", "csv Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.bankAccount",
//				"fisrst Required");
	}
}
