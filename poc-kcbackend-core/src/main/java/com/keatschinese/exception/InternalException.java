package com.keatschinese.exception;

public class InternalException extends Exception {

	private static final long serialVersionUID = -52598844067803250L;

	public InternalException(String message) {
		super(message);
	}
	
	public InternalException(String message, Exception e) {
		super(message, e);
	}
}
