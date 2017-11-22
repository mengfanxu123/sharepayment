package com.mengfan.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mengfan.spring.pojo.BankAccount;
import com.mengfan.spring.pojo.User;

public class BankAccountValiator implements Validator {
	public boolean supports(Class aClass) {
		return aClass.equals(BankAccount.class);
	}
	public void validate(Object obj, Errors errors) {
		BankAccount ba = (BankAccount) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountNum", "error.invalid.bankAccount", "AccountNumber Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "error.invalid.bankAccount", "amount Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "validate", "error.invalid.bankAccount", "vaidate Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.bankAccount", "lastName Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "csv", "error.invalid.bankAccount", "csv Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.bankAccount",
				"fisrst Required");
	}
}
