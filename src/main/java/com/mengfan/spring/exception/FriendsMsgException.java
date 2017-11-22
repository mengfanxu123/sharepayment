package com.mengfan.spring.exception;

public class FriendsMsgException extends Exception {
	public FriendsMsgException(String message) {
		super("FriendsMsgException-" + message);
	}

	public FriendsMsgException(String message, Throwable cause) {
		super("FriendsMsgException-" + message, cause);
	}

}
