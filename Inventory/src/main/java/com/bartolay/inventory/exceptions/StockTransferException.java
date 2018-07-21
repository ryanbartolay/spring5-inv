package com.bartolay.inventory.exceptions;

public class StockTransferException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public StockTransferException(String string) {
		super(string);
	}

}
