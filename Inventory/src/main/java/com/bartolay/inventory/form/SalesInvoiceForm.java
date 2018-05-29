package com.bartolay.inventory.form;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.sales.entity.CreditCardDetails;
import com.bartolay.inventory.sales.entity.SalesInvoiceItem;

public class SalesInvoiceForm {
	
	private String system_number;

	@NotNull(message="Stock Opening year date is required!")
	@Size(min=4, max=4, message="Incorrect Year format")
	@Digits(fraction = 0, integer = 4, message="Year only accepts digits.")
	private String year;
	
	@NotNull(message="Transaction Date is required!")
	@DateTimeFormat(pattern="MM/dd/YY")
	private Date transactionDate;
	
	@Size(min=4, max=20, message="Document Number is Required. Length between 4-20 characters.")
	@NotNull(message="Stock Opening document number is required!")
	private String documentNumber;
	
	@Max(value=100, message="Discount is over the maximum value 100.")
	@Min(value=0, message="Discount is below the minimum value 0.")
	private BigDecimal discountPercentage;
	
	private String description;
	
	@NotNull(message="Stock Opening location is required!")
	private Location location;
	
	@NotNull(message="You must choose a payment method")
	private PaymentMethod paymentMethod;
	
	private CreditCardDetails creditCardDetails;

	@NotNull
	private User customer;
	
	@NotNull
	private User salesPerson;
	
	@NotNull(message="Needed atleast 1 item")
	private List<SalesInvoiceItem> salesInvoiceItems;

	
	public String getSystem_number() {
		return system_number;
	}

	public void setSystem_number(String system_number) {
		this.system_number = system_number;
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

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
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

	public User getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(User salesPerson) {
		this.salesPerson = salesPerson;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public List<SalesInvoiceItem> getSalesInvoiceItems() {
		return salesInvoiceItems;
	}

	public void setSalesInvoiceItems(List<SalesInvoiceItem> salesInvoiceItems) {
		this.salesInvoiceItems = salesInvoiceItems;
	}

	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

}
