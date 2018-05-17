package com.bartolay.inventory.form;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreditCardDetailsForm {

	private int id;
	
	@Size(max=50)
	@NotNull(message="Holders name is required")
	private String holders_name;
	
	@Size(max=25)
	@NotNull(message="Card Number is required")
	private String cardNumber;
	
	@Column(name="month_expiry", length=2, nullable=false)
	private int monthExpiry;
	
	@Column(name="year_expiry", length=4, nullable=false)
	private int yearExpiry;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHolders_name() {
		return holders_name;
	}

	public void setHolders_name(String holders_name) {
		this.holders_name = holders_name;
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
