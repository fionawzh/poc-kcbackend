package com.keatschinese.http;

public class CommonResponse<T> {

	public enum ResponseStatus {
		SUCCESS, FAILED
	};
	
	public CommonResponse(ResponseStatus status) {
		this.status = status;
	}
	
	public CommonResponse(ResponseStatus status, T response) {
		this.status = status;
		this.response = response;
	}
	
	public CommonResponse(ResponseStatus status, String message) {
		this.status = status;
		this.errorMessage = message;
	}
	
	private ResponseStatus status;
	private String errorMessage;
	private T response;
	
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
}
