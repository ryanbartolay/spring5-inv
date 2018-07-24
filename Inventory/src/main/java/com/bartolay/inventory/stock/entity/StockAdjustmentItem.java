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
	
	@Column(name="quantity_previous", nullable=false, precision=10, scale=5)
	private BigDecimal quantityPrevious;
	
	@Column(name="quantity", nullable=false, precision=10, scale=5)
	private BigDecimal quantity;
	
	@Column(name="quantity_adjusted", nullable=false, precision=10, scale=5)
	private BigDecimal quantityAdjusted;
	
	@Column(name="cost_previous", nullable=false, precision=10, scale=5)
	private BigDecimal costPrevious;
	
	@Column(name="cost", nullable=false, precision=10, scale=5)
	private BigDecimal cost;
	
	@Column(name="cost_adjusted", nullable=false, precision=10, scale=5)
	private BigDecimal costAdjusted;
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getQuantityPrevious() {
		return quantityPrevious;
	}
	public void setQuantityPrevious(BigDecimal quantityPrevious) {
		this.quantityPrevious = quantityPrevious;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getCostPrevious() {
		return costPrevious;
	}
	public void setCostPrevious(BigDecimal costPrevious) {
		this.costPrevious = costPrevious;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public BigDecimal getQuantityAdjusted() {
		return quantityAdjusted;
	}
	public void setQuantityAdjusted(BigDecimal quantityAdjusted) {
		this.quantityAdjusted = quantityAdjusted;
	}
	public BigDecimal getCostAdjusted() {
		return costAdjusted;
	}
	public void setCostAdjusted(BigDecimal costAdjusted) {
		this.costAdjusted = costAdjusted;
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
