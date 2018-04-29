package com.bartolay.inventory.model;

import org.springframework.validation.BindingResult;

public class RestApiException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private BindingResult bindingResult;
	private String message;
	private Exception exception;
	
	public RestApiException(String message) {
		this.message = message;
	}
	
	public RestApiException(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
	
	public RestApiException(Exception exception) {
		this.exception = exception;
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}

	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
}
