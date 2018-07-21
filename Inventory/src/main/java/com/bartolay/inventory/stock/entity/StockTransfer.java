package com.bartolay.inventory.stock.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.repositories.GeneratedStockTransferSystemNumber;

@Entity
@Table(name="stock_transfer")
public class StockTransfer implements GeneratedStockTransferSystemNumber {
	
	public final static String TABLE_NAME = "stock_transfer";
	
	@Id
	@GeneratedValue(generator = "StockTransferSystemNumberGenerator")
	@GenericGenerator(name = "StockTransferSystemNumberGenerator", strategy = "com.bartolay.inventory.entity.generators.StockTransferSystemNumberGenerator")
	@Column(name="system_number", nullable=false, unique=true, insertable=false, updatable=false, length=15)
	private String systemNumber;
	
	@Column(name="document_number", unique=true, length=25)
	private String documentNumber;
	
	@Column(name="transaction_date")
	private Date transactionDate;
	
	@Column(name="year", nullable=false, length=4, updatable=false)
	private String year;
	
	@Lob
	private String description;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="from_location_id")
	private Location fromLocation;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="to_location_id")
	private Location toLocation;
	
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
	
	@OneToMany(mappedBy = "stockTransfer", fetch=FetchType.LAZY)
	private List<StockTransferItem> stockTransferItems;
	
	public StockTransfer() {
		super();
	}

	public StockTransfer(String systemNumber) {
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

	public Location getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(Location fromLocation) {
		this.fromLocation = fromLocation;
	}

	public Location getToLocation() {
		return toLocation;
	}

	public void setToLocation(Location toLocation) {
		this.toLocation = toLocation;
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
	
	public List<StockTransferItem> getStockTransferItems() {
		return stockTransferItems;
	}

	public void setStockTransferItems(List<StockTransferItem> stockTransferItems) {
		this.stockTransferItems = stockTransferItems;
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

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String toString() {
		return "StockTransfer [systemNumber=" + systemNumber + ", documentNumber=" + documentNumber
				+ ", transactionDate=" + transactionDate + ", year=" + year + ", fromLocation=" + fromLocation
				+ ", toLocation=" + toLocation + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", updatedDated=" + updatedDated + ", updatedBy=" + updatedBy + ", stockTransferItems="
				+ stockTransferItems + "]";
	}
}
