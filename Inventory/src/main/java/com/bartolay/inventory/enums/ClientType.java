package com.bartolay.inventory.enums;

public enum ClientType {
	CUSTOMER("Customer"),
	SUPPLIER("Supplier");
	
	private String name;
	
	private ClientType(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
