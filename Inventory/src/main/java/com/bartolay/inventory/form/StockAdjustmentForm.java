package com.bartolay.inventory.form;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.Unit;
import com.bartolay.inventory.stock.entity.StockAdjustment;

public class StockAdjustmentForm {
	
	private String system_number;
	
	@NotNull(message="Stock Opening document number is required!")
	private String document_number;
	
	private String description;
	
	@NotNull(message="Stock Opening location is required!")
	private Location location;
	
	@NotNull(message="Stock Opening year date is required!")
	@Size(min=4, max=4, message="Incorrect Year format")
	@Digits(fraction = 0, integer = 4, message="Year only accepts digits.")
	private String year;
	
	private Date transaction_date;
	
	@NotNull (message="Required adjustment type")
	private Unit adjustmentType;
	
	@NotNull(message="Needed atleast 1 item")
	private Set<StockAdjustment> items = new HashSet<>();

	public String getSystem_number() {
		return system_number;
	}

	public void setSystem_number(String system_number) {
		this.system_number = system_number;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	public Unit getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(Unit adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	public Set<StockAdjustment> getItems() {
		return items;
	}

	public void setItems(Set<StockAdjustment> items) {
		this.items = items;
	}
}