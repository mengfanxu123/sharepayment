package com.mengfan.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mengfan.spring.pojo.TransferHistory;

//import com.mengfan.spring.pojo.BankAccount;

public class TransferValidator implements Validator {
	public boolean supports(Class aClass) {
		return aClass.equals(TransferHistory.class);
	}
	public void validate(Object obj, Errors errors) {
		TransferHistory tf = (TransferHistory) obj;
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reciever", "error.invalid.tf", "reciever Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "error.invalid.tf", "amount Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "recieverLastName", "error.invalid.tf", "lastName Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.tf", "firstName Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comment", "error.invalid.tf", "comment Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.tf",
//				"fisrst Required");
	}
}
