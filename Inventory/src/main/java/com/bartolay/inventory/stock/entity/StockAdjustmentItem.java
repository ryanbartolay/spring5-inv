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

import com.bartolay.inventory.entity.Inventory;

@Entity
@Table(name="stock_adjustment_item")
public class StockAdjustmentItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_adjustment_item_generator")
	@SequenceGenerator(name="stock_adjustment_item_generator", sequenceName = "STOCK_ADJUSTMENT_ITEM_SER_SEQ")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="inventory_id", nullable=false, updatable=true)
	private Inventory inventory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false, updatable=true)
	private StockAdjustment stockAdjustment;
	
	@Column(name="amount", nullable=false, precision=10, scale=5)
	private BigDecimal amount;	
	
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public StockAdjustment getStockAdjustment() {
		return stockAdjustment;
	}
	public void setStockAdjustment(StockAdjustment stockAdjustment) {
		this.stockAdjustment = stockAdjustment;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		StockAdjustmentItem other = (StockAdjustmentItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
