package com.bartolay.inventory.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiResponse {

	private HttpStatus status;
	private String message;
	private List<String> errors;

	public ApiResponse(String message) {
		this.message = message;
	}
	
	public ApiResponse(HttpStatus status) {
		this.status = status;
	}
	
	public ApiResponse(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public ApiResponse(HttpStatus status, List<String> errors) {
		this.status = status;
		this.errors = errors;
	}
	
	public ApiResponse(HttpStatus status, String message, List<String> errors) {
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public ApiResponse(HttpStatus status, String message, String error) {
		this.status = status;
		this.message = message;
		errors = Arrays.asList(error);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getLocalizedMessage() {
		return message;
	}

	public void setLocalizedMessage(String localizedMessage) {
		this.message = localizedMessage;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ApiError [badRequest=" + status + ", localizedMessage=" + message + ", errors=" + errors
				+ "]";
	}
}
