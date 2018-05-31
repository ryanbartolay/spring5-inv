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

import org.hibernate.annotations.CreationTimestamp;

import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.Unit;
import com.bartolay.inventory.entity.User;

@Entity
public class StockReceiveItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_receive_item_generator")
	@SequenceGenerator(name="stock_receive_item_generator", sequenceName = "STOCK_RECEIVEITEM_SER_SEQ")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable=false, updatable=true)
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false, updatable=false)
	private StockReceive stockReceive;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="unit_id", nullable=false, updatable=false)
	private Unit unit;
	
	@Column(name="quantity", nullable=false, precision=10, scale=5, updatable=false)
	private BigDecimal quantity;
	
	@Column(name="unit_cost", nullable=false, precision=10, scale=5, updatable=false)
	private BigDecimal unitCost;
	
	@Column(name="created_date", nullable=false, updatable=false)
	@CreationTimestamp
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable=false, updatable=false)
	private User createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public StockReceive getStockReceive() {
		return stockReceive;
	}

	public void setStockReceive(StockReceive stockReceive) {
		this.stockReceive = stockReceive;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
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

	@Override
	public String toString() {
		return "StockReceiveItem [id=" + id + ", item=" + item + ", stockReceive=" + stockReceive + ", unit=" + unit
				+ ", quantity=" + quantity + ", unitCost=" + unitCost + ", createdDate=" + createdDate + ", createdBy="
				+ createdBy + "]";
	}
}
