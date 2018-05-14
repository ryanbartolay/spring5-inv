package com.bartolay.inventory.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.enums.SaleTransactionStatus;

@Deprecated
@Entity
@Table(name="sales_transaction_item")
public class SaleTransactionItem {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_generator")
	@SequenceGenerator(name="unit_generator", sequenceName = "UNIT_SER_SEQ")
	private int id;
	
	@Column(name="system_number", nullable=false, unique=false, insertable=true, updatable=false, length=10)
	private String systemNumber;
	
	@Column(name="transaction_type", nullable=false, unique=false, insertable=true, updatable=false, length=10)
	private SaleTransactionStatus transactionType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id", nullable=false, updatable=false)
	private Item item;
	
	@Column(name="quantity", updatable=true)
	private BigDecimal quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=false, updatable=false)
	private Location location;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSystemNumber() {
		return systemNumber;
	}

	public void setSystemNumber(String systemNumber) {
		this.systemNumber = systemNumber;
	}

	public SaleTransactionStatus getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(SaleTransactionStatus transactionType) {
		this.transactionType = transactionType;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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
	
}
