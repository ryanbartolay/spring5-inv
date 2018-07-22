package com.bartolay.inventory.stock.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.repositories.GeneratedSystemNumber;

@Entity
@Table(name="stock_adjustment")
public class StockAdjustment implements GeneratedSystemNumber {
	@Transient
	public static final String TABLE_NAME = "stock_adjustment";

	@Id
	@GeneratedValue(generator = "StockAdjustmentSystemNumberGenerator")
	@GenericGenerator(name = "StockAdjustmentSystemNumberGenerator", strategy = "com.bartolay.inventory.entity.generators.SystemNumberGenerator")
	@Column(name="system_number", nullable=false, unique=true, insertable=false, updatable=false, length=10)
	private String systemNumber;

	@Column(name="document_number", unique=true, length=25)
	private String documentNumber;
	
	@Column(name="transaction_date", nullable=false)
	@Type(type="date")
	private Date transactionDate;

	@Column(name="year", nullable=false, length=4, updatable=false)
	private String year;
	
	@OneToMany(mappedBy = "stockAdjustment", fetch=FetchType.LAZY)
	private List<StockAdjustmentItem> stockAdjustmentItems;
	
	private String description;
	
	@Column(name="stock_adjustment_type", nullable=false, updatable=false)
	private String stockAdjustmentType;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="stock_adjustment_reason_id", nullable=false, insertable=true, updatable=true)
	private StockAdjustmentReason stockAdjustmentReason;
	
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

	public List<StockAdjustmentItem> getStockAdjustmentItems() {
		return stockAdjustmentItems;
	}

	public void setStockAdjustmentItems(List<StockAdjustmentItem> stockAdjustmentItems) {
		this.stockAdjustmentItems = stockAdjustmentItems;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StockAdjustmentReason getStockAdjustmentReason() {
		return stockAdjustmentReason;
	}

	public void setStockAdjustmentReason(StockAdjustmentReason stockAdjustmentReason) {
		this.stockAdjustmentReason = stockAdjustmentReason;
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
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	public String getStockAdjustmentType() {
		return stockAdjustmentType;
	}

	public void setStockAdjustmentType(String stockAdjustmentType) {
		this.stockAdjustmentType = stockAdjustmentType;
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
		StockAdjustment other = (StockAdjustment) obj;
		if (systemNumber == null) {
			if (other.systemNumber != null)
				return false;
		} else if (!systemNumber.equals(other.systemNumber))
			return false;
		return true;
	}

}
