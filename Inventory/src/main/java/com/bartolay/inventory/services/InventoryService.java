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
import com.bartolay.inventory.enums.Status;
import com.bartolay.inventory.enums.TransactionType;
import com.bartolay.inventory.repositories.InventoryRepository;
import com.bartolay.inventory.repositories.InventoryTransactionRepository;
import com.bartolay.inventory.repositories.ItemUnitRepository;
import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.repositories.SalesInvoiceRepository;
import com.bartolay.inventory.stock.entity.StockOpening;
import com.bartolay.inventory.stock.repositories.StockOpeningRepository;
import com.bartolay.inventory.utils.UserCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
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
	private SalesInvoiceRepository salesInvoiceRepository; 

	@Autowired
	private StockOpeningRepository stockOpeningRepository;
	
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Creates the Stock Opening
	 * @param stockOpening
	 */
	@Transactional
	public void createStockOpening(StockOpening stockOpening) {
		// TODO should have stock opening items

		// Lets save the stock opening first so we can generate a system number
		stockOpening.setStatus(Status.CREATED);
		stockOpening.setCreatedBy(userCredentials.getLoggedInUser());
		stockOpeningRepository.save(stockOpening);

		List<Inventory> inventories = inventoryRepository.findByLocation(stockOpening.getLocation());

		List<Inventory> inventories2 = inventoryRepository.findByLocation(stockOpening.getLocation());

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

			inventoryTransaction.setRawQuantity(stockOpeningItem.getQuantity());
			inventoryTransaction.setQuantityBefore(inventory.getQuantity());

			// Now lets compute for the quantity of the item
			// if unit is the default unit of the item
			// save the quantity as is
			if(stockOpeningItem.getItem().isDefaultUnit(stockOpeningItem.getUnit())) {
				inventory.setQuantity(inventory.getQuantity().add(stockOpeningItem.getQuantity()));
				inventoryTransaction.setRateQuantity(stockOpeningItem.getQuantity());
			} else { // if unit is not the default then we compute for the actual quantity
				BigDecimal quantity = stockOpeningItem.getQuantity(); // user input quantity

				// lets get the rate of the item unit
				ItemUnit itemUnit = itemUnitRepository.findByItemAndUnit(stockOpeningItem.getItem(), stockOpeningItem.getUnit());
				BigDecimal rate = itemUnit.getRate(); // items rate

				BigDecimal rateQuantity = quantity.divide(rate, 5, RoundingMode.HALF_UP);
				inventoryTransaction.setRateQuantity(rateQuantity);

				inventory.setQuantity(inventory.getQuantity().add(rateQuantity));
			}

			inventoryTransaction.setQuantityAfter(inventory.getQuantity());
			inventoryTransaction.setItem(stockOpeningItem.getItem());
			inventoryTransaction.setLocation(stockOpening.getLocation());
			inventoryTransaction.setTransactionSystemNumber(stockOpening.getSystemNumber());
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


	/**
	 * This creates a Sales Invoice,
	 * - will create and invoice if, 
	 * 		- the item inventory exists
	 * 		- quantity is less than the inventory quantity
	 * @param salesInvoice
	 */
	@Transactional
	public void createSalesInvoice(SalesInvoice salesInvoice) {
		// TODO should have sales invoice item

		// Lets save the sales invoice first so we can generate a system number
		// this will be essential in case theres a wrong data on the sales invoice
		salesInvoice.setCreatedBy(userCredentials.getLoggedInUser());
		salesInvoice.setStatus(Status.CREATED);
		salesInvoiceRepository.save(salesInvoice);

		// we get the inventories by location
		List<Inventory> inventories = inventoryRepository.findByLocation(salesInvoice.getLocation());

		List<InventoryTransaction> inventoryTransactions = new ArrayList<>(); // just a placeholder for one commit only
		List<Inventory> inventoriesForSave = new ArrayList<>(); // just a placeholder for one commit only

		salesInvoice.getInventoryTransactions().forEach(inventoryTransaction -> {

			Inventory inventory = new Inventory();

			inventory.setItem(inventoryTransaction.getItem());
			inventory.setLocation(salesInvoice.getLocation());

			// checking if the item is already existing in the inventory, if not we continue the stream
			// we will only process what is existing in the inventory (you cannot put sales invoice with inventory not existing, lol)
			if(!inventories.contains(inventory)) {
				return;
			}

			inventory = inventories.get(inventories.indexOf(inventory));
			inventory.setUpdatedBy(userCredentials.getLoggedInUser());

			inventoryTransaction.setQuantityBefore(inventory.getQuantity());

			// Now lets compute for the quantity of the item
			// if unit is the default unit of the item
			// save the quantity as is
			if(inventory.getItem().isDefaultUnit(inventoryTransaction.getUnit())) { 
				inventory.setQuantity(inventory.getQuantity().subtract(inventoryTransaction.getRawQuantity()));
				inventoryTransaction.setRateQuantity(inventoryTransaction.getRawQuantity());
			} else {
				BigDecimal quantity = inventoryTransaction.getRawQuantity(); // user input quantity

				// lets get the rate of the item unit
				ItemUnit itemUnit = itemUnitRepository.findByItemAndUnit(inventoryTransaction.getItem(), inventoryTransaction.getUnit());
				BigDecimal rate = itemUnit.getRate(); // items rate

				BigDecimal rateQuantity = quantity.divide(rate, 5, RoundingMode.HALF_UP);
				inventoryTransaction.setRateQuantity(rateQuantity);

				inventory.setQuantity(inventory.getQuantity().subtract(rateQuantity));
			}

			inventoryTransaction.setQuantityAfter(inventory.getQuantity());
			inventoryTransaction.setTransactionSystemNumber(salesInvoice.getSystemNumber());
			inventoryTransaction.setTransactionType(TransactionType.SALES_INVOICE);
			inventoryTransaction.setCreatedBy(userCredentials.getLoggedInUser());
			inventoryTransaction.setInventory(inventory);
			inventoryTransaction.setLocation(salesInvoice.getLocation());

			inventoryTransactions.add(inventoryTransaction);
		});

		inventoryTransactionRepository.saveAll(inventoryTransactions);
		inventoryRepository.saveAll(inventoriesForSave);
	}

	@Transactional
	public void cancelSalesInvoice(SalesInvoice salesInvoice) {
		
		System.err.println(salesInvoice);

		final SalesInvoice updatedSalesInvoice = salesInvoiceRepository.findById(salesInvoice.getSystemNumber()).get();

		List<Inventory> inventories = new ArrayList<>(); 
		List<InventoryTransaction> inventoryTransactions = new ArrayList<>(); 

		updatedSalesInvoice.setStatus(Status.CANCELLED);
		updatedSalesInvoice.setUpdatedBy(userCredentials.getLoggedInUser());

		// we get all the inventory transactions for this invoice
		List<InventoryTransaction> transactions = inventoryTransactionRepository.findByTransactionSystemNumberAndTransactionType(updatedSalesInvoice.getSystemNumber(), TransactionType.SALES_INVOICE);

		transactions.forEach(transaction -> {
			
			InventoryTransaction cancelledTransaction = new InventoryTransaction();
			cancelledTransaction.setTransactionSystemNumber(updatedSalesInvoice.getSystemNumber());
			cancelledTransaction.setTransactionType(TransactionType.SALES_CANCEL_INVOICE);
			cancelledTransaction.setUnitCost(transaction.getUnitCost());
			cancelledTransaction.setCreatedBy(userCredentials.getLoggedInUser());
			cancelledTransaction.setInventory(transaction.getInventory());
			cancelledTransaction.setItem(transaction.getItem());
			cancelledTransaction.setLocation(transaction.getLocation());
			cancelledTransaction.setUnit(transaction.getUnit());

			Inventory inventory = transaction.getInventory();

			cancelledTransaction.setRawQuantity(inventory.getQuantity());
			cancelledTransaction.setQuantityBefore(inventory.getQuantity());

			// lets rollback the quantity to the inventory
			BigDecimal rateQuantity = transaction.getRateQuantity();

			// we add up the rate quantity to the total quantity of the inventory
			inventory.setQuantity(inventory.getQuantity().add(rateQuantity));
			
			cancelledTransaction.setRateQuantity(rateQuantity);
			cancelledTransaction.setQuantityAfter(inventory.getQuantity());

			inventories.add(inventory);
			inventoryTransactions.add(cancelledTransaction);
		});

		salesInvoiceRepository.save(updatedSalesInvoice);
		inventoryRepository.saveAll(inventories);
		inventoryTransactionRepository.saveAll(inventoryTransactions);
	}
	
	
}
