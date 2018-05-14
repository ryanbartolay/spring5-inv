package com.bartolay.inventory.sales.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.bartolay.inventory.entity.InventoryTransaction;
import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.enums.Status;
import com.bartolay.inventory.repositories.GeneratedSystemNumber;

@Entity
@Table(name="sales_invoice")
public class SalesInvoice implements GeneratedSystemNumber {

	@Transient
	public static final String TABLE_NAME = "sales_invoice";
	
	@Transient
	private Set<InventoryTransaction> intentoryTransactions;

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=false, updatable=false)
	private Location location;
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_method", nullable=false, length=10, updatable=true)
	private PaymentMethod paymentMethod;
	
	@Column(name="status", nullable=false, updatable=true, length=10)
	private Status status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "credit_card_details_id", nullable=true, updatable=true)
	private CreditCardDetails creditCardDetails;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sales_person_id", nullable=true, updatable=true)
	private User salesPerson;
	
	@Column(name="created_date", nullable=false, updatable=false)
	@CreationTimestamp
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by_id", nullable=false, updatable=false)
	private User createdBy;

	@Column(name="updated_date")
	@UpdateTimestamp
	private Date updatedDated;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by_id", nullable=true, updatable=true)
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
	public User getSalesPerson() {
		return salesPerson;
	}
	public void setSalesPerson(User salesPerson) {
		this.salesPerson = salesPerson;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDated() {
		return updatedDated;
	}

	public void setUpdatedDated(Date updatedDated) {
		this.updatedDated = updatedDated;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public Set<InventoryTransaction> getIntentoryTransactions() {
		return intentoryTransactions;
	}
	public void setIntentoryTransactions(Set<InventoryTransaction> intentoryTransactions) {
		this.intentoryTransactions = intentoryTransactions;
	}
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}
	
	@Override
	public String toString() {
		return "SalesInvoice [systemNumber=" + systemNumber + ", year=" + year + ", transactionDate=" + transactionDate
				+ ", documentNumber=" + documentNumber + ", description=" + description + ", location=" + location
				+ ", paymentMethod=" + paymentMethod + ", status=" + status + ", creditCardDetails="
				+ creditCardDetails + ", salesPerson=" + salesPerson + ", createdDate=" + createdDate + ", createdBy="
				+ createdBy + ", updatedDated=" + updatedDated + ", updatedBy=" + updatedBy + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((systemNumber == null) ? 0 : systemNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesInvoice other = (SalesInvoice) obj;
		if (systemNumber == null) {
			if (other.systemNumber != null)
				return false;
		} else if (!systemNumber.equals(other.systemNumber))
			return false;
		return true;
	}
	
}
