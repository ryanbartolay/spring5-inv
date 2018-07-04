package com.bartolay.inventory.form;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.Supplier;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.sales.entity.CreditCardDetails;
import com.bartolay.inventory.stock.entity.StockReceivedExpense;
import com.bartolay.inventory.stock.entity.StockReceivedItem;

public class StockReceivedForm {
	
	private String systemNumber;
	
	@NotNull(message="Transaction Date is required!")
	@DateTimeFormat(pattern="MM/dd/YY")
	private Date transactionDate;
	
	@Size(min=4, max=20, message="Document Number is Required. Length between 4-20 characters.")
	@NotNull(message="Stock Opening document number is required!")
	private String document_number;
	
	@NotNull(message="Location is required" )
	private Location location;
	
	@NotNull(message="Please specify Payment Method")
	private PaymentMethod paymentMethod;
	
	private CreditCardDetails creditCardDetails;
	
	@NotNull(message="Year is required")
	@Size(min=4, max=4, message="Incorrect Year format")
	@Digits(fraction = 0, integer = 4, message="Year only accepts digits.")
	private String year;
	
	private List<StockReceivedExpense> expenses;
	
	@Min(value=0, message="Minimum Discount Value is 0")
	@Max(value=100, message="Maximum Discount value is 100")
	private int discountValue = 0;
	
	@NotNull(message="Supplier is required")
	private Supplier supplier;
	
	@NotNull(message="Atleast 1 item is required")
	private List<StockReceivedItem> stockReceiveItems;
	
	private String description;

	public String getSystemNumber() {
		return systemNumber;
	}

	public void setSystemNumber(String systemNumber) {
		this.systemNumber = systemNumber;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDocument_number() {
		return document_number;
	}

	public void setDocument_number(String document_number) {
		this.document_number = document_number;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public CreditCardDetails getCreditCardDetails() {
		return creditCardDetails;
	}

	public void setCreditCardDetails(CreditCardDetails creditCardDetails) {
		this.creditCardDetails = creditCardDetails;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<StockReceivedExpense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<StockReceivedExpense> expenses) {
		this.expenses = expenses;
	}

	public int getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(int discountValue) {
		this.discountValue = discountValue;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<StockReceivedItem> getStockReceiveItems() {
		return stockReceiveItems;
	}

	public void setStockReceiveItems(List<StockReceivedItem> stockReceiveItems) {
		this.stockReceiveItems = stockReceiveItems;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "StockReceivedForm [systemNumber=" + systemNumber + ", transactionDate=" + transactionDate
				+ ", document_number=" + document_number + ", location=" + location + ", paymentMethod=" + paymentMethod
				+ ", creditCardDetails=" + creditCardDetails + ", year=" + year + ", expenses=" + expenses
				+ ", discountValue=" + discountValue + ", supplier=" + supplier + ", stockReceiveItems="
				+ stockReceiveItems + ", description=" + description + "]";
	}
	
	
}
