package com.mengfan.spring.exception;

public class TransferException extends Exception{
	public TransferException(String message)
	{
		super("TransferException-"+message);
	}
	public TransferException(String message, Throwable cause)
	{
		super("TransferException-"+message,cause);
	}


}
