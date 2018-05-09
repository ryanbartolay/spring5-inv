package com.bartolay.inventory.form;

import java.util.Date;

import com.bartolay.inventory.entity.Location;

import io.micrometer.core.lang.NonNull;

public class OpeningStockForm {
	private Long id;
	
	private String document_number;
	
	private String description;
	
	@NonNull
	private Location location;
	
	@NonNull
	private Date transaction_date;

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
}
