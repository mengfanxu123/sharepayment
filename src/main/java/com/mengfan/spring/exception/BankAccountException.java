package com.mengfan.spring.exception;

public class BankAccountException extends Exception {
	public BankAccountException(String message)
	{
		super("BankAccountException-"+message);
	}
	
	public BankAccountException(String message, Throwable cause)
	{
		super("BankException-"+message,cause);
	}

}
