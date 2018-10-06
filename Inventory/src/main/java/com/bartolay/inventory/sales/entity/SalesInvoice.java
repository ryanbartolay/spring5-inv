package com.bartolay.inventory.sales.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.enums.SalesInvoiceStatus;
import com.bartolay.inventory.repositories.GeneratedSystemNumber;

@Entity
@Table(name = "sales_invoice", indexes = {
		@Index(columnList="system_number", name="sales_invoice_systemNumber"),
		@Index(columnList="document_number", name="sales_invoice_documentNumber"),
})
public class SalesInvoice implements GeneratedSystemNumber {
	@Transient
	public static final String INVOICE_CANCELLED = "SALES_INVOICE_CANCELLED";

	@Id
	@GeneratedValue(generator = "SalesInvoice-UniqueIdGenerator")
	@GenericGenerator(name = "SalesInvoice-UniqueIdGenerator", strategy = "com.bartolay.inventory.entity.generators.SystemNumberGenerator")
	@Column(name = "system_number", nullable = false, unique = true, insertable = false, updatable = false, length = 10)
	private String systemNumber;

	@Column(name = "year", nullable = false, length = 4, updatable = false)
	private String year;

	@Column(name = "transaction_date", nullable = false)
	private Date transactionDate;

	@Column(name = "document_number", unique = true, length = 25)
	private String documentNumber;

	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id", nullable = false, updatable = false)
	private Location location;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_method", nullable = false, length = 10, updatable = true)
	private PaymentMethod paymentMethod;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "credit_card_details_id", nullable = true, updatable = true)
	private CreditCardDetails creditCardDetails;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sales_person_id", nullable = true, updatable = true)
	private User salesPerson;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false, updatable = true)
	private User customer;

	@Column(name = "discount_percentage", nullable = false, precision = 3, scale = 2)
	private BigDecimal discountPercentage;

	@Column(name = "discount_total", nullable = false, precision = 10, scale = 5)
	private BigDecimal discountTotal;

	@Column(name = "subtotal", nullable = false, precision = 10, scale = 5)
	private BigDecimal subtotal;

	@Column(name = "net_total", nullable = false, precision = 10, scale = 5)
	private BigDecimal netTotal;

	@Column(name = "created_date", nullable = false, updatable = false)
	@CreationTimestamp
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by_id", nullable = false, updatable = false)
	private User createdBy;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private Date updatedDated;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by_id", nullable = true, updatable = true)
	private User updatedBy;

	@OneToMany(mappedBy = "salesInvoice", fetch = FetchType.LAZY)
	private List<SalesInvoiceItem> salesInvoiceItems;

	@Column(name = "status", nullable = false, updatable = true, length = 10)
	private SalesInvoiceStatus salesInvoiceStatus;

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

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
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

	@Override
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public BigDecimal getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(BigDecimal netTotal) {
		this.netTotal = netTotal;
	}

	public BigDecimal getDiscountTotal() {
		return discountTotal;
	}

	public void setDiscountTotal(BigDecimal discountTotal) {
		this.discountTotal = discountTotal;
	}

	@Override
	public String getTableName() {
		return "sales_invoice";
	}

	public SalesInvoiceStatus getSalesInvoiceStatus() {
		return salesInvoiceStatus;
	}

	public void setSalesInvoiceStatus(SalesInvoiceStatus salesInvoiceStatus) {
		this.salesInvoiceStatus = salesInvoiceStatus;
	}

	@Override
	public String toString() {
		return "SalesInvoice [systemNumber=" + systemNumber + ", year=" + year + ", transactionDate=" + transactionDate
				+ ", documentNumber=" + documentNumber + ", description=" + description
//				+ ", location=" + location
				+ ", paymentMethod=" + paymentMethod + ", status=" + salesInvoiceStatus + ", creditCardDetails="
				+ creditCardDetails
//				+ ", customer="+ customer 
//				+ ", salesPerson=" + salesPerson 
				+ ", createdDate=" + createdDate
//				+ ", createdBy=" + createdBy 
				+ ", updatedDated=" + updatedDated
//				+ ", updatedBy=" + updatedBy 
				+ "]";
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
