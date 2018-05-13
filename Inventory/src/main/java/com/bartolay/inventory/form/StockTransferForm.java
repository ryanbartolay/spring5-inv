package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;

import com.bartolay.inventory.entity.Location;

public class StockTransferForm {

	private String systemNumber;
	
	@NotNull(message="From location is required!" )
	private Location fromLocation;
	
	@NotNull(message="To location is required!" )
	private Location toLocation;

	public String getSystemNumber() {
		return systemNumber;
	}

	public void setSystemNumber(String systemNumber) {
		this.systemNumber = systemNumber;
	}

	public Location getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(Location fromLocation) {
		this.fromLocation = fromLocation;
	}

	public Location getToLocation() {
		return toLocation;
	}

	public void setToLocation(Location toLocation) {
		this.toLocation = toLocation;
	}
	
	
	
}
