package com.keatschinese.exception;

public class ClientOrientedException extends Exception {

	private static final long serialVersionUID = -2583102228914474230L;

	public ClientOrientedException(String message) {
		super(message);
	}
	
	public ClientOrientedException(String message, Exception e) {
		super(message, e);
	}
}
