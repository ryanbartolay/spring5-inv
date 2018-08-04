package com.bartolay.inventory.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.bartolay.inventory.entity.Inventory;

public class StockAdjustmentItemForm {

	@NotNull(message="Inventory is Required for Stock Adjustment Item")
	private Inventory inventory;
	
	@NotNull(message="Required On Hand value")
	private BigDecimal on_hand;
	
	@NotNull(message="Unit Cost is Required in Stock Adjustment Item")
	private BigDecimal unit_cost;

	@NotNull(message="Required amount")
	private BigDecimal amount;
	
	private String description;

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public BigDecimal getOn_hand() {
		return on_hand;
	}

	public void setOn_hand(BigDecimal on_hand) {
		this.on_hand = on_hand;
	}

	public BigDecimal getUnit_cost() {
		return unit_cost;
	}

	public void setUnit_cost(BigDecimal unit_cost) {
		this.unit_cost = unit_cost;
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
	public String toString() {
		return "StockAdjustmentItemForm [inventory=" + inventory + ", on_hand=" + on_hand + ", unit_cost=" + unit_cost
				+ ", amount=" + amount + ", description=" + description + "]";
	}
	
}
