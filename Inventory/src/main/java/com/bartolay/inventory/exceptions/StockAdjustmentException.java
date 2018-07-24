package com.bartolay.inventory.exceptions;

import java.util.List;

public class StockAdjustmentException extends Exception {

	private static final long serialVersionUID = 1L;
	private List<String> errors;

	public StockAdjustmentException(String message) {
		super(message);
	}
	
	public StockAdjustmentException(String message, List<String> errors) {
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
