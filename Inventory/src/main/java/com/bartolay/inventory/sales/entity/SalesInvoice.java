package com.bartolay.inventory.sales.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.repositories.GeneratedSystemNumber;

@Entity
@Table(name="sales_invoice")
public class SalesInvoice implements GeneratedSystemNumber {

	@Transient
	public static final String TABLE_NAME = "sales_invoice";

	@Id
	@GeneratedValue(generator = "SalesInvoice-UniqueIdGenerator")
	@GenericGenerator(name = "SalesInvoice-UniqueIdGenerator", strategy = "com.bartolay.inventory.entity.generators.SystemNumberGenerator")
	@Column(name="system_number", nullable=false, unique=true, insertable=false, updatable=false, length=10)
	private String systemNumber;
	
	@Column(name="year", nullable=false, length=4, updatable=false)
	private String year;
	
	@Column(name="transaction_date", nullable=false)
	private Date transactionDate;
	
	@Column(name="document_number", unique=true, length=25)
	private String documentNumber;
	
	private String description;
	
	private Location location;
	
	@Column(name="payment_method", nullable=false, length=10, updatable=true)
	private PaymentMethod paymentMethod;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "credit_card_id", nullable=false, updatable=false)
	private CreditCardDetails creditCardDetails;
	
	@Column(name="created_date", nullable=false, updatable=true)
	private User salesPerson;
	
	@Column(name="created_date", nullable=false, updatable=false)
	@CreationTimestamp
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable=false, updatable=false)
	private User createdBy;

	@Column(name="updated_date")
	@UpdateTimestamp
	private Date updatedDated;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable=true, updatable=true)
	private User updatedBy;

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

	@Override
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
}
