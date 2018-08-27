package com.bartolay.inventory.exceptions;

import java.util.List;

public class CurrencyException extends Exception {

	private static final long serialVersionUID = 1L;
	private List<String> errors;
	
	public CurrencyException(String message) {
		super(message);
	}

	public CurrencyException(String message, List<String> errors) {
		super(message);
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
}
