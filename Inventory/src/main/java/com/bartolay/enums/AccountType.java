package com.bartolay.enums;

public enum AccountType {
	SUPERADMIN("Super Administrator"),
	ADMIN("Administrator"),
	USER("User");
	
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
