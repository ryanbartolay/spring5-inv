package com.bartolay.inventory.sales.entity;

public class CreditCardDetails {
	
	private int id;
	
	private String holdersName;
	
	private String cardNumber;
	
	private int monthExpiry;
	
	private int yearExpiry;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHoldersName() {
		return holdersName;
	}

	public void setHoldersName(String holdersName) {
		this.holdersName = holdersName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getMonthExpiry() {
		return monthExpiry;
	}

	public void setMonthExpiry(int monthExpiry) {
		this.monthExpiry = monthExpiry;
	}

	public int getYearExpiry() {
		return yearExpiry;
	}

	public void setYearExpiry(int yearExpiry) {
		this.yearExpiry = yearExpiry;
	}

}
