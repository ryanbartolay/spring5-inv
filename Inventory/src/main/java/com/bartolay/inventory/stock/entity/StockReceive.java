package com.bartolay.inventory.stock.entity;

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

@Entity
public class StockReceive implements GeneratedSystemNumber {

	@Id
	@GeneratedValue(generator = "StockReceive-UniqueIdGenerator")
	@GenericGenerator(name = "StockReceive-UniqueIdGenerator", strategy = "com.bartolay.inventory.entity.generators.SystemNumberGenerator")
	@Column(name="system_number", nullable=false, unique=true, insertable=false, updatable=false, length=10)
	private String systemNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id", nullable=false)
	private Supplier supplier;

	@Enumerated(EnumType.STRING)
	@Column(name="payment_method", nullable=false, length=10, updatable=true)
	private PaymentMethod paymentMethod;

	@Column(name="document_number", unique=true, length=25)
	private String documentNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=false, updatable=false)
	private Location location;
	
	@OneToMany(mappedBy = "stockReceive", fetch=FetchType.LAZY)
	private List<StockReceiveExpense> expenses;

	@Column(name="discount_value", nullable=false)
	private Integer discountValue;

	@Column(name="year", nullable=false, length=4, updatable=false)
	private String year;

	@OneToMany(mappedBy = "stockReceive", fetch=FetchType.LAZY)
	private List<StockReceiveItem> stockReceiveItems;

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

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Integer getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(Integer discountValue) {
		this.discountValue = discountValue;
	}

	public List<StockReceiveItem> getStockReceiveItems() {
		return stockReceiveItems;
	}

	public void setStockReceiveItems(List<StockReceiveItem> stockReceiveItems) {
		this.stockReceiveItems = stockReceiveItems;
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
		return "stock_receive";
	}

	public List<StockReceiveExpense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<StockReceiveExpense> expenses) {
		this.expenses = expenses;
	}

	@Override
	public String toString() {
		return "StockReceive [systemNumber=" + systemNumber + ", supplier=" + supplier + ", paymentMethod="
				+ paymentMethod + ", documentNumber=" + documentNumber + ", location=" + location + ", expenses="
				+ expenses + ", discountValue=" + discountValue + ", year=" + year + ", stockReceiveItems="
				+ stockReceiveItems + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedDated="
				+ updatedDated + ", updatedBy=" + updatedBy + "]";
	}

}