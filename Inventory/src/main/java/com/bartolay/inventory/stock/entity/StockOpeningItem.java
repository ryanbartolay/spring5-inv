package com.bartolay.inventory.stock.entity;

import java.math.BigDecimal;

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

import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.Unit;
import com.bartolay.inventory.enums.Status;

@Entity
@Table(name="stock_opening_item")
public class StockOpeningItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_opening_item_generator")
	@SequenceGenerator(name="stock_opening_item_generator", sequenceName = "STOCK_OPENING_ITEM_SER_SEQ")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id", nullable=false, updatable=true)
	private Item item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false, updatable=true)
	private StockOpening stockOpening;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="unit_id", nullable=false, updatable=true)
	private Unit unit;
	
	@Column(name="unit_cost", nullable=false, precision=10, scale=5)
	private BigDecimal unitCost;
	
	@Column(name="quantity", nullable=false, precision=10, scale=5)
	private BigDecimal quantity;
	
	@Column(name="rate_quantity", nullable=false, precision=10, scale=5)
	private BigDecimal rateQuantity;
	
	@Column(name="unit_cost_total", nullable=false, precision=10, scale=5)
	private BigDecimal unitCostTotal;
	
	@Column(name="status", nullable=false, updatable=true, length=3)
	private Status status;
	
	public StockOpeningItem() {
		
	}
	public StockOpeningItem(Integer id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public BigDecimal getUnitCostTotal() {
		return unitCostTotal;
	}
	public void setUnitCostTotal(BigDecimal unitCostTotal) {
		this.unitCostTotal = unitCostTotal;
	}
	public StockOpening getStockOpening() {
		return stockOpening;
	}
	public void setStockOpening(StockOpening stockOpening) {
		this.stockOpening = stockOpening;
	}
	public BigDecimal getRateQuantity() {
		return rateQuantity;
	}
	public void setRateQuantity(BigDecimal rateQuantity) {
		this.rateQuantity = rateQuantity;
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "StockOpeningItem [id=" + id + ", item=" + item + ", unit=" + unit + ", unitCost=" + unitCost
				+ ", quantity=" + quantity + "]";
	}
}
