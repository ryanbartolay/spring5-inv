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
import com.bartolay.inventory.entity.ItemUnit;

@Entity
@Table(name="stock_opening_item")
public class StockOpeningItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_opening_item_generator")
	@SequenceGenerator(name="stock_opening_item_generator", sequenceName = "STOCK_OPENING_ITEM_SER_SEQ")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id", nullable=false, updatable=true)
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="unit_id", nullable=false, updatable=true)
	private ItemUnit itemUnit;
	
	@Column(name="unit_cost", nullable=false)
	private BigDecimal unitCost;
	
	@Column(name="quantity", nullable=false)
	private BigDecimal quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="stock_opening_id", nullable=false, updatable=true)
	private StockOpening stockOpening;
	
	
	public StockOpeningItem() {
		
	}
	public StockOpeningItem(Long id) {
		this.id = id;
	}
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
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public StockOpening getStockOpening() {
		return stockOpening;
	}
	public void setStockOpening(StockOpening stockOpening) {
		this.stockOpening = stockOpening;
	}
	public ItemUnit getItemUnit() {
		return itemUnit;
	}
	public void setItemUnit(ItemUnit itemUnit) {
		this.itemUnit = itemUnit;
	}
	public BigDecimal getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
	}
	@Override
	public String toString() {
		return "StockOpeningItem [id=" + id + ", item=" + item + ", itemUnit=" + itemUnit + ", unitCost=" + unitCost
				+ ", quantity=" + quantity + ", stockOpening=" + stockOpening + "]";
	}
}
