package com.bartolay.inventory.form;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.stock.entity.StockTransferItem;

public class StockTransferForm {

	@NotNull(message="Transaction Date is required!")
	@DateTimeFormat(pattern="MM/dd/YY")
	private Date transactionDate;
	
	@Size(min=4, max=20, message="Document Number is Required. Length between 4-20 characters.")
	@NotNull(message="Stock Opening document number is required!")
	private String document_number;
	
	private String description;
	
	@NotNull(message="Year is required!")
	@Size(min=4, max=4, message="Incorrect Year format")
	@Digits(fraction = 0, integer = 4, message="Year only accepts digits.")
	private String year;
	
	@NotNull(message="From location is required!" )
	private Location fromLocation;
	
	@NotNull(message="To location is required!" )
	private Location toLocation;

	@NotNull(message="Needed atleast 1 item")
	private List<StockTransferItem> stockTransferItems;
	
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public List<StockTransferItem> getStockTransferItems() {
		return stockTransferItems;
	}

	public void setStockTransferItems(List<StockTransferItem> stockTransferItems) {
		this.stockTransferItems = stockTransferItems;
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
