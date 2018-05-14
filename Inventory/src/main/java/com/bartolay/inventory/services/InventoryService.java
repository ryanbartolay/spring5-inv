package com.bartolay.inventory.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.entity.Inventory;
import com.bartolay.inventory.entity.InventoryTransaction;
import com.bartolay.inventory.entity.ItemUnit;
import com.bartolay.inventory.enums.TransactionType;
import com.bartolay.inventory.repositories.InventoryRepository;
import com.bartolay.inventory.repositories.InventoryTransactionRepository;
import com.bartolay.inventory.repositories.ItemUnitRepository;
import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.stock.entity.StockOpening;
import com.bartolay.inventory.stock.repositories.StockOpeningRepository;
import com.bartolay.inventory.utils.UserCredentials;

@Service
@Transactional
public class InventoryService {
	
	@Autowired 
	private UserCredentials userCredentials;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private InventoryTransactionRepository inventoryTransactionRepository;
	
	@Autowired
	private ItemUnitRepository itemUnitRepository;
	
	@Autowired
	private StockOpeningRepository stockOpeningRepository;
	
	/**
	 * Creates the Stock Opening
	 * @param stockOpening
	 */
	public void createStockOpening(StockOpening stockOpening) {
		
		// Lets save the stock opening first so we can generate a system number
		stockOpeningRepository.save(stockOpening);
		
		List<Inventory> inventories = inventoryRepository.findByLocation(stockOpening.getLocation());
		
		List<InventoryTransaction> invTransactions = new ArrayList<>();
		
		// iterate through items and check if default unit
		stockOpening.getItems().forEach(stockOpeningItem -> {
			
			Inventory inventory = new Inventory();
			InventoryTransaction inventoryTransaction = new InventoryTransaction();
			
			inventory.setItem(stockOpeningItem.getItem());
			inventory.setLocation(stockOpening.getLocation());
			
			// checking if the item is already existing in the inventory
			if(inventories.contains(inventory)) {
				inventory = inventories.get(inventories.indexOf(inventory));
				inventory.setUpdatedBy(userCredentials.getLoggedInUser());
			} else {
				inventory.setCreatedBy(userCredentials.getLoggedInUser());
				inventory.setQuantity(new BigDecimal("0"));
			}
			
			inventoryTransaction.setQuantityBefore(inventory.getQuantity());
			
			// Now lets compute for the quantity of the item
			// if unit is the default unit of the item
			// save the quantity as is
			if(stockOpeningItem.getItem().isDefaultUnit(stockOpeningItem.getUnit())) {
				inventory.setQuantity(inventory.getQuantity().add(stockOpeningItem.getQuantity()));
			} else { // if unit is not the default then we compute for the actual quantity
				BigDecimal quantity = stockOpeningItem.getQuantity(); // user input quantity
				
				// lets get the rate of the item unit
				ItemUnit itemUnit = itemUnitRepository.findByItemAndUnit(stockOpeningItem.getItem(), stockOpeningItem.getUnit());
				BigDecimal rate = itemUnit.getRate(); // items rate
				
				System.err.println("rate " + rate);
				System.err.println("quantity " + quantity);
				
				BigDecimal rateQuantity = quantity.divide(rate, 5, RoundingMode.HALF_UP);
				
				System.err.println("rateQuantity " + rateQuantity);
				
				inventory.setQuantity(inventory.getQuantity().add(rateQuantity));
			}

			inventoryTransaction.setItem(stockOpeningItem.getItem());
			inventoryTransaction.setLocation(stockOpening.getLocation());
			inventoryTransaction.setTransactionSystemNumber(stockOpening.getSystemNumber());
			inventoryTransaction.setQuantity(inventory.getQuantity());
			inventoryTransaction.setUnit(stockOpeningItem.getUnit());
			inventoryTransaction.setUnitCost(stockOpeningItem.getUnitCost());
			inventoryTransaction.setTransactionType(TransactionType.STOCK_OPENING);
			inventoryTransaction.setCreatedBy(userCredentials.getLoggedInUser());
			
			inventoryRepository.save(inventory);
			
			inventoryTransaction.setInventory(inventory);
			
			invTransactions.add(inventoryTransaction);
		});
		
		inventoryTransactionRepository.saveAll(invTransactions);
	}
	
	public void createSalesInvoice(SalesInvoice salesInvoice) {
		
	}
}
