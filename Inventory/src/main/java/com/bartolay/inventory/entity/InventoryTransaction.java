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

import com.bartolay.inventory.enums.TransactionType;

@Entity
@Table(name="inventory_transaction")
public class InventoryTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_transaction_generator")
	@SequenceGenerator(name="inventory_transaction_generator", sequenceName = "INVENTORY_TRANSACTION_SER_SEQ")
	public int id;
	
	@Column(name="transaction_type_id", nullable=false, length=3, updatable=false)
	private TransactionType transactionType;
	
	@Column(name="transaction_system_number", nullable=false, updatable=false)
	private String transactionSystemNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=false, updatable=false)
	private Location location;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id", nullable=false, updatable=false)
	public Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="unit_id", nullable=false, updatable=false)
	private Unit unit;
	
	@Column(name="unit_cost", nullable=false, precision=10, scale=5, updatable=false)
	private BigDecimal unitCost;
	
	@Column(name="quantity_before", nullable=false, precision=10, scale=5, updatable=false)
	private BigDecimal quantityBefore;
	
	@Column(name="quantity", nullable=false, precision=10, scale=5, updatable=false)
	private BigDecimal quantity;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="inventory_id", nullable=false, updatable=true)
	private Inventory inventory;
	
	@Column(name="created_date", nullable=false, updatable=false)
	@CreationTimestamp
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable=false, updatable=false)
	private User createdBy;
	
	public InventoryTransaction() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionSystemNumber() {
		return transactionSystemNumber;
	}

	public void setTransactionSystemNumber(String transactionSystemNumber) {
		this.transactionSystemNumber = transactionSystemNumber;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public BigDecimal getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
	}

	public BigDecimal getQuantityBefore() {
		return quantityBefore;
	}

	public void setQuantityBefore(BigDecimal quantityBefore) {
		this.quantityBefore = quantityBefore;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
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

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
