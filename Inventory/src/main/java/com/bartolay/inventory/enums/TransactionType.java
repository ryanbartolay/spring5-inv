package com.bartolay.inventory.enums;

@Deprecated
public enum TransactionType {
	SALES_INVOICE(1),
	SALES_CANCEL_INVOICE(11),
	
	SALES_RETURN(2),
	SALES_RECIEVE(4),
	STOCK_OPENING(5),
	STOCK_TRANSFER(6),
	STOCK_ADJUSTMENT(7);
	
	private int id;
	
	private TransactionType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
