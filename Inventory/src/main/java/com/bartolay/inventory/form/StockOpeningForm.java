package com.bartolay.inventory.form;

import java.util.Date;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.stock.entity.StockOpeningItem;

public class StockOpeningForm {
	private Long id;
	
	private String document_number;
	
	private String description;
	
	@NotNull
	private Location location;
	
	@NotNull
	private Date transaction_date;
	
	@NotNull
	private Set<StockOpeningItem> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDocument_number() {
		return document_number;
	}

	public void setDocument_number(String document_number) {
		this.document_number = document_number;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	public Set<StockOpeningItem> getItems() {
		return items;
	}

	public void setItems(Set<StockOpeningItem> items) {
		this.items = items;
	}
	
}
