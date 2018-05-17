package com.bartolay.inventory.form;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.stock.entity.StockTransferItem;

public class StockTransferForm {

	private String systemNumber;

	@NotNull(message="Transaction Date is required")
	private String transactionDate;
	
	@Size(max=25)
	private String documentNumber;
	
	@NotNull(message="From location is required!" )
	private Location fromLocation;
	
	@NotNull(message="To location is required!" )
	private Location toLocation;

	@NotNull(message="Needed atleast 1 item")
	private Set<StockTransferItem> items = new HashSet<>();

	public String getSystemNumber() {
		return systemNumber;
	}

	public void setSystemNumber(String systemNumber) {
		this.systemNumber = systemNumber;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Set<StockTransferItem> getItems() {
		return items;
	}

	public void setItems(Set<StockTransferItem> items) {
		this.items = items;
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
