package com.bartolay.inventory.stock.entity;

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

@Entity
@Table(name="stock_transfer_item")
public class StockTransferItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_item_generator")
	@SequenceGenerator(name="stock_item_generator", sequenceName = "STOCK_ITEM_SER_SEQ")
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable=false, updatable=true)
	private Item item;
	private Double unit;
	private String unitDesciption;
	private Long quantity;
	
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
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Double getUnit() {
		return unit;
	}
	public void setUnit(Double unit) {
		this.unit = unit;
	}
	public String getUnitDesciption() {
		return unitDesciption;
	}
	public void setUnitDesciption(String unitDesciption) {
		this.unitDesciption = unitDesciption;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "StockTransferItem [id=" + id + ", item=" + item + ", unit=" + unit + ", unitDesciption="
				+ unitDesciption + ", quantity=" + quantity + "]";
	}
}