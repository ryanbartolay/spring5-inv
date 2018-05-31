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
import org.hibernate.annotations.Type;

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
	
	@Column(name="unit_cost", precision=10, scale=5, updatable=false)
	private BigDecimal unitCost;
	
	@Column(name="quantity_raw", nullable=false, precision=10, scale=5, updatable=false)
	private BigDecimal rawQuantity;
	
	@Column(name="quantity_before", precision=10, scale=5, updatable=false)
	private BigDecimal quantityBefore;
	
	@Deprecated
	@Column(name="quantity_rate", nullable=false, precision=10, scale=5, updatable=false)
	private BigDecimal rateQuantity;
	
	@Column(name="quantity_after", precision=10, scale=5, updatable=false)
	private BigDecimal quantityAfter;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="inventory_id", nullable=false, updatable=true)
	private Inventory inventory;
	
	@Column(name="created_date", nullable=false, updatable=false)
	@CreationTimestamp
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable=false, updatable=false)
	private User createdBy;
	
	@Column(name="tx_json_before")
	@Type(type="text")
	private String transactionBefore;
	
	@Column(name="tx_json_after")
	@Type(type="text")
	private String transactionAfter;
	
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

	public BigDecimal getRawQuantity() {
		return rawQuantity;
	}

	public void setRawQuantity(BigDecimal rawQuantity) {
		this.rawQuantity = rawQuantity;
	}

	public BigDecimal getQuantityBefore() {
		return quantityBefore;
	}

	public void setQuantityBefore(BigDecimal quantityBefore) {
		this.quantityBefore = quantityBefore;
	}

	@Deprecated
	public BigDecimal getRateQuantity() {
		return rateQuantity;
	}

	@Deprecated
	public void setRateQuantity(BigDecimal rateQuantity) {
		this.rateQuantity = rateQuantity;
	}
	
	public BigDecimal getQuantityAfter() {
		return quantityAfter;
	}

	public void setQuantityAfter(BigDecimal quantityAfter) {
		this.quantityAfter = quantityAfter;
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

	public String getTransactionBefore() {
		return transactionBefore;
	}

	public void setTransactionBefore(String transactionBefore) {
		this.transactionBefore = transactionBefore;
	}

	public String getTransactionAfter() {
		return transactionAfter;
	}

	public void setTransactionAfter(String transactionAfter) {
		this.transactionAfter = transactionAfter;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
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
		InventoryTransaction other = (InventoryTransaction) obj;
		if (item == null) {
			if (other.item != null)
				System.err.println("1111111");
				return false;
		} else if (!item.equals(other.item)) {
			
			System.err.println("222222");
			System.err.println(item);
			System.err.println(other.item);
			System.err.println("222222");
			return false;
		}
		
		if (location == null) {
			if (other.location != null) {
				System.err.println("3333333");
				return false;
			}
		} else if (!location.equals(other.location)) {
			System.err.println("444444444444");
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "InventoryTransaction [id=" + id + ", transactionType=" + transactionType + ", transactionSystemNumber="
				+ transactionSystemNumber + ",  location=" + location
				+ ", item=" + item + ", unit=" + unit + ", unitCost=" + unitCost + ", rawQuantity=" + rawQuantity
				+ ", quantityBefore=" + quantityBefore + ", rateQuantity=" + rateQuantity + ", quantityAfter="
				+ quantityAfter + ", createdDate=" + createdDate + ", transactionBefore=" + transactionBefore
				+ ", transactionAfter=" + transactionAfter + "]";
	}
	
	
}
