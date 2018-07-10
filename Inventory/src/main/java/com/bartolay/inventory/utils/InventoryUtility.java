package com.bartolay.inventory.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.Inventory;
import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.Unit;
import com.bartolay.inventory.exceptions.SalesInvoiceException;
import com.bartolay.inventory.sales.entity.SalesInvoiceItem;

@Component
public class InventoryUtility {

	public Inventory findInventoryFromList(List<Inventory> inventories, int location_id, int item_id, int unit_id) {
		return inventories.stream().filter(
				inventory -> 
					inventory.getLocation().getId() == location_id 
				&& 	inventory.getItem().getId() == item_id
				&& 	inventory.getUnit().getId() == unit_id)
				.findAny().orElse(null);
	}

	public Inventory findInventoryFromList(List<Inventory> inventories, Location location, Item item, Unit unit) {
		try {
			return findInventoryFromList(inventories, location.getId(), item.getId(), unit.getId());
		} catch(Exception e) {
			return null;
		}
		
	}
	
	public SalesInvoiceItem subtractQuantitySalesInvoiceItem(SalesInvoiceItem salesInvoiceItem, BigDecimal transaction_quantity) throws SalesInvoiceException {
		
		BigDecimal transaction_quantity_cost = transaction_quantity.multiply(salesInvoiceItem.getUnitCost()).divide(salesInvoiceItem.getQuantity(), 2, BigDecimal.ROUND_HALF_UP);
		
		
		if(salesInvoiceItem.getQuantity().compareTo(transaction_quantity) < 0) {
			throw new SalesInvoiceException("Transaction ("+ transaction_quantity +") is more than the current stock quantity ("+ salesInvoiceItem.getQuantity() +").");
		}
		
		salesInvoiceItem.setQuantity(salesInvoiceItem.getQuantity().subtract(transaction_quantity).setScale(2, BigDecimal.ROUND_HALF_UP));
		salesInvoiceItem.setUnitCostTotal(salesInvoiceItem.getUnitCostTotal().subtract(transaction_quantity_cost).setScale(2, BigDecimal.ROUND_HALF_UP));
		
		return salesInvoiceItem;
	}
	
	public SalesInvoiceItem addQuantitySalesInvoiceItem(SalesInvoiceItem salesInvoiceItem, BigDecimal transaction_quantity) throws SalesInvoiceException {
		
		BigDecimal pricePerItem = salesInvoiceItem.getUnitCost().divide(salesInvoiceItem.getQuantity(), 2, BigDecimal.ROUND_HALF_UP);
		
		BigDecimal quantity = salesInvoiceItem.getQuantity().add(transaction_quantity);
		
		salesInvoiceItem.setQuantity(quantity);
		salesInvoiceItem.setUnitCostTotal(pricePerItem.multiply(quantity));
		
		return salesInvoiceItem;
	}
	
}
