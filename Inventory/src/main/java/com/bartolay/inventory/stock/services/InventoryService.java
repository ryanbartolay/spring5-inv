package com.bartolay.inventory.stock.services;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.entity.Inventory;
import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.ItemUnit;
import com.bartolay.inventory.repositories.InventoryRepository;
import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.stock.entity.StockOpening;
import com.bartolay.inventory.utils.UserCredentials;

@Service
@Transactional
public class InventoryService {
	
	@Autowired 
	private UserCredentials userCredentials;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	/**
	 * Creates the Stock Opening
	 * @param stockOpening
	 */
	public void createStockOpening(StockOpening stockOpening) {
		
		List<Inventory> inventories = inventoryRepository.findByLocation(stockOpening.getLocation());
		
		List<ItemUnit> itemPlaceholder = null;
		
		// iterate through items and check if default unit
		stockOpening.getItems().forEach(stockOpeningItem -> {
			
			Inventory inventory = new Inventory();
			inventory.setItem(stockOpeningItem.getItem());
			inventory.setLocation(stockOpening.getLocation());
			
			// checking if the item is already existing in the inventory
			if(inventories.contains(inventory)) {
				inventory = inventories.get(inventories.indexOf(inventory));
				inventory.setUpdatedBy(userCredentials.getLoggedInUser());
			} else {
				inventory.setCreatedBy(userCredentials.getLoggedInUser());
			}
			
			// if unit is the default unit of the item
			// save the quantity as is
			if(stockOpeningItem.getItem().isDefaultUnit(stockOpeningItem.getItemUnit().getUnit())) {
				inventory.setQuantity(inventory.getQuantity().add(stockOpeningItem.getQuantity()));
			} else { // if unit is not the default then we compute for the actual quantity
				BigDecimal quantity = stockOpeningItem.getQuantity(); // user input quantity
				
				BigDecimal rate = stockOpeningItem.getItemUnit().getRate(); // items rate
				
				BigDecimal rateQuantity = quantity.divide(rate);
				
				inventory.setQuantity(inventory.getQuantity().add(rateQuantity));
			}
			
			inventoryRepository.save(inventory);
		});
		
	}
	
	public void createSalesInvoice(SalesInvoice salesInvoice) {
		
	}
}
