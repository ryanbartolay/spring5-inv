package com.bartolay.inventory.enums;

public enum AccountType {
	ROOT("Super Administrator"),
	ADMIN("Administrator"),
	USER("User"),
	SALES("Sales");
	
	private String name;
	
	private AccountType(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
