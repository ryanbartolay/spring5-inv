package com.bartolay.inventory.stock.entity;

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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.Supplier;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.repositories.GeneratedSystemNumber;
import com.bartolay.inventory.sales.entity.CreditCardDetails;

@Entity
public class StockReceived implements GeneratedSystemNumber {

	@Id
	@GeneratedValue(generator = "StockReceived-UniqueIdGenerator")
	@GenericGenerator(name = "StockReceived-UniqueIdGenerator", strategy = "com.bartolay.inventory.entity.generators.SystemNumberGenerator")
	@Column(name="system_number", nullable=false, unique=true, insertable=false, updatable=false, length=10)
	private String systemNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id", nullable=false)
	private Supplier supplier;

	@Enumerated(EnumType.STRING)
	@Column(name="payment_method", nullable=false, length=10, updatable=true)
	private PaymentMethod paymentMethod;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "credit_card_details_id", nullable=true, updatable=true)
	private CreditCardDetails creditCardDetails;

	@Column(name="document_number", unique=true, length=25)
	private String documentNumber;
	
	@Lob
	@Column
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=false, updatable=false)
	private Location location;
	
	@Column(name="transaction_date", nullable=false)
	private Date transactionDate;
	
	@OneToMany(mappedBy = "stockReceive", fetch=FetchType.LAZY)
	private List<StockReceivedExpense> stockReceiveExpenses;
	
	@Column(name="expense_total", nullable=false, precision=10, scale=5)
	private BigDecimal expensesTotal;

	@Column(name="discount_value", nullable=false)
	private Integer discountValue;

	@Column(name="year", nullable=false, length=4, updatable=false)
	private String year;

	@OneToMany(mappedBy = "stockReceive", fetch=FetchType.LAZY)
	private List<StockReceivedItem> stockReceiveItems;
	
	@Column(name="total", nullable=false, precision=10, scale=5)
	private BigDecimal total;
	
	@Column(name="grand_total", nullable=false, precision=10, scale=5)
	private BigDecimal grandTotal;
	
	@Column(name="net_total", nullable=false, precision=10, scale=5)
	private BigDecimal netTotal;

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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public BigDecimal getExpensesTotal() {
		return expensesTotal;
	}

	public void setExpensesTotal(BigDecimal expensesTotal) {
		this.expensesTotal = expensesTotal;
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

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Integer getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(Integer discountValue) {
		this.discountValue = discountValue;
	}

	public List<StockReceivedItem> getStockReceiveItems() {
		return stockReceiveItems;
	}

	public void setStockReceiveItems(List<StockReceivedItem> stockReceiveItems) {
		this.stockReceiveItems = stockReceiveItems;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
	
	public BigDecimal getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(BigDecimal netTotal) {
		this.netTotal = netTotal;
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

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getYear() {
		return this.year;
	}

	@Override
	public Location getLocation() {
		return this.location;
	}

	@Override
	public String getTableName() {
		return "stock_received";
	}

	public List<StockReceivedExpense> getStockReceiveExpenses() {
		return stockReceiveExpenses;
	}

	public void setStockReceiveExpenses(List<StockReceivedExpense> stockReceiveExpenses) {
		this.stockReceiveExpenses = stockReceiveExpenses;
	}

	@Override
	public String toString() {
		return "StockReceive [systemNumber=" + systemNumber + ", supplier=" + supplier + ", paymentMethod="
				+ paymentMethod + ", documentNumber=" + documentNumber + ", location=" + location + ", expenses="
				+ stockReceiveExpenses + ", discountValue=" + discountValue + ", year=" + year + ", total=" + total 
				+ ",createdDate=" + createdDate + ", updatedDated="
				+ updatedDated + "]";
	}

}
