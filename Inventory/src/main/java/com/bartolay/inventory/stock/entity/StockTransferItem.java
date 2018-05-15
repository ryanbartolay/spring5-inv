package com.bartolay.inventory.stock.entity;

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

import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.Unit;
import com.bartolay.inventory.entity.User;

@Entity
@Table(name="stock_transfer_item")
public class StockTransferItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_transfer_item_generator")
	@SequenceGenerator(name="stock_transfer_item_generator", sequenceName = "STOCK_TRANSFERITEM_SER_SEQ")
	private Long id;
	
	@Column(name="transaction_system_number", nullable=false, updatable=false)
	private String transactionSystemNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable=false, updatable=true)
	private Item item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="unit_id", nullable=false, updatable=false)
	private Unit unit;
	
	@Column(name="quantity_raw", nullable=false, precision=10, scale=5, updatable=false)
	private BigDecimal rawQuantity;
	
	@Column(name="quantity_rate", nullable=false, precision=10, scale=5, updatable=false)
	private BigDecimal rateQuantity;
	
	@Column(name="quantity_to_before", nullable=false, precision=10, scale=5, updatable=false)
	private BigDecimal quantityToBefore;
	
	@Column(name="quantity_to_after", nullable=false, precision=10, scale=5, updatable=false)
	private BigDecimal quantityToAfter;
	
	@Column(name="quantity_from_before", nullable=false, precision=10, scale=5, updatable=false)
	private BigDecimal quantityFromBefore;
	
	@Column(name="quantity_from_after", nullable=false, precision=10, scale=5, updatable=false)
	private BigDecimal quantityFromAfter;
	
	@Column(name="created_date", nullable=false, updatable=false)
	@CreationTimestamp
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable=false, updatable=false)
	private User createdBy;
	
	public StockTransferItem() {
		
	}
	public StockTransferItem(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTransactionSystemNumber() {
		return transactionSystemNumber;
	}
	public void setTransactionSystemNumber(String transactionSystemNumber) {
		this.transactionSystemNumber = transactionSystemNumber;
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
	public BigDecimal getRawQuantity() {
		return rawQuantity;
	}
	public void setRawQuantity(BigDecimal rawQuantity) {
		this.rawQuantity = rawQuantity;
	}
	public BigDecimal getRateQuantity() {
		return rateQuantity;
	}
	public void setRateQuantity(BigDecimal rateQuantity) {
		this.rateQuantity = rateQuantity;
	}
	public BigDecimal getQuantityToBefore() {
		return quantityToBefore;
	}
	public void setQuantityToBefore(BigDecimal quantityToBefore) {
		this.quantityToBefore = quantityToBefore;
	}
	public BigDecimal getQuantityToAfter() {
		return quantityToAfter;
	}
	public void setQuantityToAfter(BigDecimal quantityToAfter) {
		this.quantityToAfter = quantityToAfter;
	}
	public BigDecimal getQuantityFromBefore() {
		return quantityFromBefore;
	}
	public void setQuantityFromBefore(BigDecimal quantityFromBefore) {
		this.quantityFromBefore = quantityFromBefore;
	}
	public BigDecimal getQuantityFromAfter() {
		return quantityFromAfter;
	}
	public void setQuantityFromAfter(BigDecimal quantityFromAfter) {
		this.quantityFromAfter = quantityFromAfter;
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
	
}
