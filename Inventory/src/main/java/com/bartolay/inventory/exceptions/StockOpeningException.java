package com.bartolay.inventory.exceptions;

public class StockOpeningException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public StockOpeningException(String message) {
		super(message);
	}
}
