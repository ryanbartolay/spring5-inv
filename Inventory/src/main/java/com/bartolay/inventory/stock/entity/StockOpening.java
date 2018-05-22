package com.bartolay.inventory.stock.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.entity.InventoryTransaction;
import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.Status;
import com.bartolay.inventory.repositories.GeneratedSystemNumber;

@Entity
@Table(name="stock_opening")
public class StockOpening implements GeneratedSystemNumber {

	@Transient
	public static final String TABLE_NAME = "stock_opening";
	
	@Transient
	private Set<InventoryTransaction> inventories;

	@Id
	@GeneratedValue(generator = "UniqueIdGenerator")
	@GenericGenerator(name = "UniqueIdGenerator", strategy = "com.bartolay.inventory.entity.generators.SystemNumberGenerator")
	@Column(name="system_number", nullable=false, unique=true, insertable=false, updatable=false, length=10)
	private String systemNumber;

	@Column(name="document_number", unique=true, length=25)
	private String documentNumber;

	@Column(name="transaction_date", nullable=false)
	@Type(type="date")
	private Date transactionDate;

	@Column(name="year", nullable=false, length=4, updatable=false)
	private String year;
	
	private String description;

	@OneToMany(mappedBy = "stockOpening", fetch=FetchType.LAZY)
	private Set<StockOpeningItem> items;
	
	@Column(name="total", nullable=false, precision=10, scale=5)
	private BigDecimal total;

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

	@Column(name="status", nullable=false, updatable=true, length=3)
	private Status status;
	
	@Column(name="enabled", nullable=false)
	private boolean enabled;

	public StockOpening() {
		super();
	}

	public StockOpening(String systemNumber) {
		super();
		this.systemNumber = systemNumber;
	}
	
	public String getSystemNumber() {
		return systemNumber;
	}

	public void setSystemNumber(String systemNumber) {
		this.systemNumber = systemNumber;
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

	public String getYear() {
		return year;
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
	
	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<StockOpeningItem> getItems() {
		return items;
	}

	public void setItems(Set<StockOpeningItem> items) {
		this.items = items;
	}
	
	public Set<InventoryTransaction> getInventories() {
		return inventories;
	}

	public void setInventories(Set<InventoryTransaction> inventories) {
		this.inventories = inventories;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StockOpening [inventories=" + inventories + ", systemNumber=" + systemNumber + ", documentNumber="
				+ documentNumber + ", transactionDate=" + transactionDate + ", year=" + year + ", description="
				+ description + ", items=" + items + ", total=" + total + ", location=" + location + ", createdDate="
				+ createdDate + ", createdBy=" + createdBy + ", updatedDated=" + updatedDated + ", updatedBy="
				+ updatedBy + ", status=" + status + ", enabled=" + enabled + "]";
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
		StockOpening other = (StockOpening) obj;
		if (systemNumber == null) {
			if (other.systemNumber != null)
				return false;
		} else if (!systemNumber.equals(other.systemNumber))
			return false;
		return true;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

}
