package com.bartolay.inventory.form;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.bartolay.inventory.entity.Location;

public class StockOpeningForm {
	
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
	
	@NotNull(message="Transaction Date is required!")
	@DateTimeFormat(pattern="MM/dd/YY")
	private Date transaction_date;
	
	@NotNull(message="Needed atleast 1 item")
//	private Set<StockOpeningItem> items = new HashSet<>();
	
	private List<String> items;

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

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
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
	
}
