package com.bartolay.inventory.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.entity.Inventory;
import com.bartolay.inventory.entity.InventoryTransaction;
import com.bartolay.inventory.entity.ItemUnit;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.enums.Status;
import com.bartolay.inventory.enums.TransactionType;
import com.bartolay.inventory.exceptions.StockTransferException;
import com.bartolay.inventory.repositories.InventoryRepository;
import com.bartolay.inventory.repositories.InventoryTransactionRepository;
import com.bartolay.inventory.repositories.ItemUnitRepository;
import com.bartolay.inventory.sales.entity.CreditCardDetails;
import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.entity.SalesInvoiceItem;
import com.bartolay.inventory.sales.repositories.CreditCardDetailsRepository;
import com.bartolay.inventory.sales.repositories.SalesInvoiceItemRepository;
import com.bartolay.inventory.sales.repositories.SalesInvoiceRepository;
import com.bartolay.inventory.stock.entity.StockOpening;
import com.bartolay.inventory.stock.entity.StockReceived;
import com.bartolay.inventory.stock.entity.StockReceivedExpense;
import com.bartolay.inventory.stock.entity.StockReceivedItem;
import com.bartolay.inventory.stock.entity.StockTransfer;
import com.bartolay.inventory.stock.entity.StockTransferItem;
import com.bartolay.inventory.stock.repositories.StockOpeningItemRepository;
import com.bartolay.inventory.stock.repositories.StockOpeningRepository;
import com.bartolay.inventory.stock.repositories.StockReceivedExpenseRepository;
import com.bartolay.inventory.stock.repositories.StockReceivedItemRepository;
import com.bartolay.inventory.stock.repositories.StockReceivedRepository;
import com.bartolay.inventory.stock.repositories.StockTransferItemRepository;
import com.bartolay.inventory.stock.repositories.StockTransferRepository;
import com.bartolay.inventory.utils.InventoryUtility;
import com.bartolay.inventory.utils.UserCredentials;

@Service
public class InventoryCoreService {

	@Autowired
	private CreditCardDetailsRepository creditCardDetailsRepository;
	@Autowired 
	private UserCredentials userCredentials;
	@Autowired
	private InventoryUtility inventoryUtility;
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private InventoryTransactionRepository inventoryTransactionRepository;
	@Autowired
	private ItemUnitRepository itemUnitRepository;
	@Autowired
	private SalesInvoiceRepository salesInvoiceRepository;
	@Autowired
	private SalesInvoiceItemRepository salesInvoiceItemRepository; 
	@Autowired
	private StockOpeningRepository stockOpeningRepository;
	@Autowired
	private StockOpeningItemRepository stockOpeningItemRepository;
	@Autowired
	private StockReceivedRepository stockReceiveRepository;
	@Autowired
	private StockReceivedItemRepository stockReceiveItemRepository;
	@Autowired
	private StockReceivedExpenseRepository stockReceiveExpenseRepository;
	@Autowired
	private StockTransferRepository stockTransferRepository;
	@Autowired
	private StockTransferItemRepository stockTransferItemRepository;

	/**
	 * Creates the Stock Opening
	 * @param stockOpening
	 */
	@Transactional
	public void createStockOpening(StockOpening stockOpening) {
		// TODO should have stock opening items

		// Lets save the stock opening first so we can generate a system number
		stockOpening.setStatus(Status.SUCCESS);
		stockOpening.setCreatedBy(userCredentials.getLoggedInUser());
		stockOpening.setTotal(new BigDecimal("0"));
		stockOpeningRepository.save(stockOpening);

		List<Inventory> inventories = inventoryRepository.findByLocation(stockOpening.getLocation());
		List<InventoryTransaction> invTransactions = new ArrayList<>();
		List<Inventory> inventoriesForSave = new ArrayList<>();

		// iterate through items and check if default unit
		stockOpening.getItems().forEach(stockOpeningItem -> {

			stockOpeningItem.setStockOpening(stockOpening);

			Inventory inventory = inventoryUtility.findInventoryFromList(inventories, stockOpening.getLocation(), stockOpeningItem.getItem(), stockOpeningItem.getUnit());

			if(inventory == null) {
				inventory = new Inventory();
			}

			InventoryTransaction inventoryTransaction = new InventoryTransaction();

			inventory.setItem(stockOpeningItem.getItem());
			inventory.setLocation(stockOpening.getLocation());
			inventory.setUnit(stockOpeningItem.getUnit());

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
			inventory.setQuantity(inventory.getQuantity().add(stockOpeningItem.getQuantity()));
			inventoryTransaction.setRateQuantity(stockOpeningItem.getQuantity());


			inventoryTransaction.setQuantityAfter(inventory.getQuantity());
			inventoryTransaction.setItem(stockOpeningItem.getItem());
			inventoryTransaction.setLocation(stockOpening.getLocation());
			inventoryTransaction.setTransactionSystemNumber(stockOpening.getSystemNumber());
			inventoryTransaction.setUnit(stockOpeningItem.getUnit());
			inventoryTransaction.setUnitCost(stockOpeningItem.getUnitCost());
			inventoryTransaction.setTransactionType(TransactionType.STOCK_OPENING);
			inventoryTransaction.setCreatedBy(userCredentials.getLoggedInUser());


			stockOpeningItem.setRateQuantity(inventoryTransaction.getRateQuantity());
			stockOpeningItem.setUnitCostTotal(stockOpeningItem.getUnitCost().multiply(stockOpeningItem.getRateQuantity()));
			stockOpeningItem.setStatus(Status.SUCCESS);

			stockOpening.setTotal(stockOpening.getTotal().add(stockOpeningItem.getUnitCostTotal()));

			stockOpeningItemRepository.save(stockOpeningItem);

			inventoryTransaction.setInventory(inventory);

			invTransactions.add(inventoryTransaction);
			inventoriesForSave.add(inventory);
		});

		stockOpeningRepository.save(stockOpening);

		inventoryRepository.saveAll(inventoriesForSave);
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
		salesInvoice.setStatus(Status.SUCCESS);
		salesInvoice.setSubtotal(new BigDecimal("0"));
		salesInvoice.setDiscountTotal(new BigDecimal("0"));
		salesInvoice.setNetTotal(new BigDecimal("0"));

		if(salesInvoice.getPaymentMethod().equals(PaymentMethod.CREDITCARD)) {
			salesInvoice.getCreditCardDetails().setCreatedBy(userCredentials.getLoggedInUser());
			CreditCardDetails ccDetails = creditCardDetailsRepository.save(salesInvoice.getCreditCardDetails());
			salesInvoice.setCreditCardDetails(ccDetails);
		}

		salesInvoiceRepository.save(salesInvoice);

		// we get the inventories by location
		List<Inventory> inventories = inventoryRepository.findByLocation(salesInvoice.getLocation());

		List<InventoryTransaction> inventoryTransactions = new ArrayList<>(); // just a placeholder for one commit only
		List<Inventory> inventoriesForSave = new ArrayList<>(); // just a placeholder for one commit only

		salesInvoice.getSalesInvoiceItems().forEach(salesInvoiceItem -> {
			salesInvoiceItem.setCreatedBy(userCredentials.getLoggedInUser());
			salesInvoiceItem.setSalesInvoice(salesInvoice);

			Inventory inventory = new Inventory();

			inventory.setItem(salesInvoiceItem.getItem());
			inventory.setLocation(salesInvoice.getLocation());

			// checking if the item is already existing in the inventory, if not we continue the stream
			// we will only process what is existing in the inventory (you cannot put sales invoice with inventory not existing, lol)
			if(!inventories.contains(inventory)) {
				return;
			}

			inventory = inventories.get(inventories.indexOf(inventory));
			inventory.setUpdatedBy(userCredentials.getLoggedInUser());

			// we can create transaction if inventory is found
			InventoryTransaction inventoryTransaction = new InventoryTransaction();
			inventoryTransaction.setTransactionSystemNumber(salesInvoice.getSystemNumber());
			inventoryTransaction.setLocation(salesInvoice.getLocation());
			inventoryTransaction.setTransactionType(TransactionType.SALES_INVOICE);
			inventoryTransaction.setCreatedBy(userCredentials.getLoggedInUser());
			inventoryTransaction.setInventory(inventory);
			inventoryTransaction.setItem(salesInvoiceItem.getItem());
			inventoryTransaction.setUnit(salesInvoiceItem.getUnit());
			inventoryTransaction.setRawQuantity(salesInvoiceItem.getQuantity());
			inventoryTransaction.setUnitCost(salesInvoiceItem.getUnitCost());
			inventoryTransaction.setQuantityBefore(inventory.getQuantity());

			// Now lets compute for the quantity of the item
			// if unit is the default unit of the item
			// save the quantity as is
			if(inventory.getItem().isDefaultUnit(salesInvoiceItem.getUnit())) { 
				inventory.setQuantity(inventory.getQuantity().subtract(salesInvoiceItem.getQuantity()));
				inventoryTransaction.setRateQuantity(salesInvoiceItem.getQuantity());
			} else {
				BigDecimal quantity = salesInvoiceItem.getQuantity(); // user input quantity

				// lets get the rate of the item unit
				ItemUnit itemUnit = itemUnitRepository.findByItemAndUnit(salesInvoiceItem.getItem(), salesInvoiceItem.getUnit());
				BigDecimal rate = itemUnit.getRate(); // items rate

				BigDecimal rateQuantity = quantity.divide(rate, 5, RoundingMode.HALF_UP);
				inventoryTransaction.setRateQuantity(rateQuantity);

				inventory.setQuantity(inventory.getQuantity().subtract(rateQuantity));
			}

			salesInvoiceItem.setRateQuantity(inventoryTransaction.getRateQuantity());
			salesInvoiceItem.setUnitCostTotal(salesInvoiceItem.getUnitCost().multiply(salesInvoiceItem.getRateQuantity()));
			salesInvoiceItem.setStatus(Status.SUCCESS);

			salesInvoice.setSubtotal(salesInvoice.getSubtotal().add(salesInvoiceItem.getUnitCostTotal()));
			salesInvoice.setDiscountTotal(salesInvoice.getSubtotal().multiply(salesInvoice.getDiscountPercentage().divide(new BigDecimal("100"))));
			salesInvoice.setNetTotal(salesInvoice.getSubtotal().subtract(salesInvoice.getDiscountTotal()));

			inventoryTransaction.setQuantityAfter(inventory.getQuantity());

			salesInvoiceItemRepository.save(salesInvoiceItem);

			inventoryTransaction.setItem(salesInvoiceItem.getItem());

			inventoriesForSave.add(inventory);
			inventoryTransactions.add(inventoryTransaction);
		});

		salesInvoiceRepository.save(salesInvoice);
		inventoryTransactionRepository.saveAll(inventoryTransactions);
		inventoryRepository.saveAll(inventoriesForSave);
	}

	@Transactional
	public void cancelSalesInvoice(SalesInvoice salesInvoice) {

		final SalesInvoice updatedSalesInvoice = salesInvoiceRepository.findById(salesInvoice.getSystemNumber()).get();

		List<SalesInvoiceItem> salesInvoiceItems = salesInvoiceItemRepository.findBySalesInvoice(updatedSalesInvoice);

		List<Inventory> inventories = new ArrayList<>(); 
		List<InventoryTransaction> inventoryTransactions = inventoryTransactionRepository.findByTransactionSystemNumberAndTransactionType(updatedSalesInvoice.getSystemNumber(), TransactionType.SALES_INVOICE); 

		updatedSalesInvoice.setStatus(Status.CANCELLED);
		updatedSalesInvoice.setUpdatedBy(userCredentials.getLoggedInUser());

		salesInvoiceItems.forEach(salesInvoiceItem -> {
			InventoryTransaction cancelledTransaction = new InventoryTransaction();
			cancelledTransaction.setItem(salesInvoiceItem.getItem());
			cancelledTransaction.setLocation(updatedSalesInvoice.getLocation());

			// we find the transaction to cancel first
			InventoryTransaction foundTransaction = inventoryTransactions.stream()
					.filter(t -> t.getItem().getId() == cancelledTransaction.getItem().getId() 
					&& t.getLocation().getId() == cancelledTransaction.getLocation().getId())
					.findAny()
					.orElse(null);

			if(inventoryTransactions != null) {

				Inventory inventory = foundTransaction.getInventory();

				cancelledTransaction.setTransactionSystemNumber(updatedSalesInvoice.getSystemNumber());
				cancelledTransaction.setTransactionType(TransactionType.SALES_CANCEL_INVOICE);
				cancelledTransaction.setRawQuantity(salesInvoiceItem.getQuantity());
				cancelledTransaction.setUnitCost(salesInvoiceItem.getUnitCost());
				cancelledTransaction.setCreatedBy(userCredentials.getLoggedInUser());
				cancelledTransaction.setUnit(salesInvoiceItem.getUnit());
				cancelledTransaction.setInventory(inventory);

				cancelledTransaction.setRawQuantity(inventory.getQuantity());
				cancelledTransaction.setQuantityBefore(inventory.getQuantity());

				// lets rollback the quantity to the inventory
				BigDecimal rateQuantity = foundTransaction.getRateQuantity();

				// we add up the rate quantity to the total quantity of the inventory
				inventory.setQuantity(inventory.getQuantity().add(rateQuantity));

				cancelledTransaction.setRateQuantity(rateQuantity);
				cancelledTransaction.setQuantityAfter(inventory.getQuantity());

				salesInvoiceItem.setStatus(Status.CANCELLED);

				inventories.add(inventory);
				inventoryTransactions.add(cancelledTransaction);

			}

		});

		salesInvoiceRepository.save(updatedSalesInvoice);
		salesInvoiceItemRepository.saveAll(salesInvoiceItems);
		inventoryRepository.saveAll(inventories);
		inventoryTransactionRepository.saveAll(inventoryTransactions);

	}

	@Transactional
	public void createStockTransfer(StockTransfer stockTransfer) throws StockTransferException {

		// lets save the stock transfer first to generate system number
		stockTransfer.setCreatedBy(userCredentials.getLoggedInUser());
		stockTransferRepository.save(stockTransfer);

		// we get the inventories by FROM and TO location
		List<Inventory> fromInventories = inventoryRepository.findByLocation(stockTransfer.getFromLocation());
		List<Inventory> toInventories = inventoryRepository.findByLocation(stockTransfer.getToLocation());

		// this list will be saved as new inventory transaction
		List<InventoryTransaction> inventoryTransactions = new ArrayList<>(); // just a placeholder for one commit only
		List<Inventory> inventoriesForSave = new ArrayList<>(); // just a placeholder for one commit only

		if(stockTransfer.getStockTransferItems().size() <= 0) {
			throw new StockTransferException("Atleast 1 Item is Required");
		}
		
		for(StockTransferItem stockTransferItem : stockTransfer.getStockTransferItems()) {

			stockTransferItem.setStockTransfer(stockTransfer);
			stockTransferItem.setCreatedBy(userCredentials.getLoggedInUser());

			InventoryTransaction inventoryTransaction = new InventoryTransaction();
			inventoryTransaction.setItem(stockTransferItem.getItem());
			inventoryTransaction.setLocation(stockTransfer.getFromLocation());
			inventoryTransaction.setUnit(stockTransferItem.getUnit());
			inventoryTransaction.setTransactionType(TransactionType.STOCK_TRANSFER);
			inventoryTransaction.setRawQuantity(stockTransferItem.getQuantity());
			inventoryTransaction.setTransactionSystemNumber(stockTransfer.getSystemNumber());
			inventoryTransaction.setCreatedBy(userCredentials.getLoggedInUser());

			Inventory fromInventory = new Inventory();

			fromInventory.setItem(stockTransferItem.getItem());
			fromInventory.setLocation(stockTransfer.getFromLocation());
			fromInventory.setUnit(stockTransferItem.getUnit());

			// we check if FROM already exists
			fromInventory = inventoryUtility.findInventoryFromList(
					fromInventories, 
					stockTransfer.getFromLocation().getId(), 
					stockTransferItem.getItem().getId(),
					stockTransferItem.getUnit().getId());

			if(fromInventory == null) {
				throw new StockTransferException("Stock "+ fromInventory.getItem().getId() +" with unit "+ fromInventory.getUnit().getId() +" not found from source location. " + stockTransfer.getFromLocation().getId());
			}

			inventoryTransaction.setInventory(fromInventory);

			// we also need to check if the TO already exists
			Inventory toInventory = inventoryUtility.findInventoryFromList(
					toInventories, 
					stockTransfer.getFromLocation().getId(), 
					stockTransferItem.getItem().getId(),
					stockTransferItem.getUnit().getId());

			if(toInventory == null) {
				toInventory = new Inventory();
				toInventory.setItem(stockTransferItem.getItem());
				toInventory.setLocation(stockTransfer.getFromLocation());
				toInventory.setUnit(stockTransferItem.getUnit());
				toInventory.setQuantity(new BigDecimal("0"));
				toInventory.setCreatedBy(userCredentials.getLoggedInUser());
			}

			// Inventory Transaction
			// before transaction
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("from_quantity", fromInventory.getQuantity());
			jsonObj.put("to_quantity", toInventory.getQuantity());

			inventoryTransaction.setTransactionBefore(jsonObj.toString());

			// transactions FROM and TO
			// we subtract from FROM
			BigDecimal newFromQuantity = fromInventory.getQuantity().subtract(stockTransferItem.getQuantity());

			if(newFromQuantity.compareTo(BigDecimal.ZERO) < 0) {
				throw new StockTransferException("You cannot transfer ("+ stockTransferItem.getQuantity() +") more than the current stock quantity "+ fromInventory.getQuantity() +".");
			}

			fromInventory.setQuantity(newFromQuantity);
			toInventory.setQuantity(toInventory.getQuantity().add(stockTransferItem.getQuantity()));
			inventoryTransaction.setRateQuantity(stockTransferItem.getQuantity());

			// after transaction
			jsonObj.put("from_quantity", fromInventory.getQuantity());
			jsonObj.put("to_quantity", toInventory.getQuantity());

			inventoryTransaction.setTransactionAfter(jsonObj.toString());

			inventoryTransactions.add(inventoryTransaction);
			inventoriesForSave.add(fromInventory);
			inventoriesForSave.add(toInventory);
		};

		stockTransferItemRepository.saveAll(stockTransfer.getStockTransferItems());
		inventoryTransactionRepository.saveAll(inventoryTransactions);
		inventoryRepository.saveAll(inventoriesForSave);
	}

	public void createStockReceive(StockReceived stockReceive) {

		// lets generate systemNumber by saving the stockreceive
		stockReceive.setCreatedBy(userCredentials.getLoggedInUser());
		stockReceive.setExpensesTotal(new BigDecimal("0"));
		stockReceive.setTotal(new BigDecimal("0"));
		stockReceive.setGrandTotal(new BigDecimal("0"));
		stockReceive.setNetTotal(new BigDecimal("0"));
		stockReceive = stockReceiveRepository.save(stockReceive);
		
		// expenses
		if(stockReceive.getStockReceiveExpenses() != null) {
			for(StockReceivedExpense expense : stockReceive.getStockReceiveExpenses()) {
				expense.setStockReceive(stockReceive);
				expense.setCreatedBy(userCredentials.getLoggedInUser());
				stockReceive.setExpensesTotal(stockReceive.getExpensesTotal().add(expense.getAmount()));
			}
		}

		List<Inventory> inventories = inventoryRepository.findByLocation(stockReceive.getLocation());
		List<Inventory> inventoriesForSave = new ArrayList<>();
		List<InventoryTransaction> inventoryTransactionsForSave = new ArrayList<>();
		
		for(StockReceivedItem stockReceiveItem : stockReceive.getStockReceiveItems()) {
			
			stockReceiveItem.setStockReceive(stockReceive);
			stockReceiveItem.setCreatedBy(userCredentials.getLoggedInUser());
			
			Inventory inventory = inventoryUtility.findInventoryFromList(inventories, stockReceive.getLocation(), stockReceiveItem.getItem(), stockReceiveItem.getUnit());
			
			if(inventory == null) {
				inventory = new Inventory();
				inventory.setLocation(stockReceive.getLocation());
				inventory.setItem(stockReceiveItem.getItem());
				inventory.setUnit(stockReceiveItem.getUnit());
				inventory.setQuantity(new BigDecimal("0"));
				inventory.setCreatedBy(userCredentials.getLoggedInUser());
			} else {
				inventory.setUpdatedBy(userCredentials.getLoggedInUser());
			}
			
			// inventory transaction
			InventoryTransaction inventoryTransaction = new InventoryTransaction();
			inventoryTransaction.setItem(stockReceiveItem.getItem());
			inventoryTransaction.setLocation(stockReceive.getLocation());
			inventoryTransaction.setUnit(stockReceiveItem.getUnit());
			inventoryTransaction.setTransactionType(TransactionType.STOCK_RECIEVE);
			inventoryTransaction.setRawQuantity(stockReceiveItem.getQuantity());
			inventoryTransaction.setTransactionSystemNumber(stockReceive.getSystemNumber());
			inventoryTransaction.setCreatedBy(userCredentials.getLoggedInUser());
			inventoryTransaction.setRateQuantity(stockReceiveItem.getQuantity());
			inventoryTransaction.setInventory(inventory);
			
			inventoryTransaction.setQuantityBefore(inventory.getQuantity());
			// increment quantity
			inventory.setQuantity(inventory.getQuantity().add(stockReceiveItem.getQuantity()));
			
			inventoryTransaction.setQuantityAfter(inventory.getQuantity());
			
			stockReceiveItem.setUnitCostTotal(stockReceiveItem.getQuantity().multiply(stockReceiveItem.getUnitCost()));
			
			stockReceive.setTotal(stockReceive.getTotal().add(stockReceiveItem.getUnitCostTotal()));
			
			inventoriesForSave.add(inventory);
			inventoryTransactionsForSave.add(inventoryTransaction);
		}
		
		if(stockReceive.getStockReceiveExpenses() != null && stockReceive.getStockReceiveExpenses().size() > 0) {
			stockReceiveExpenseRepository.saveAll(stockReceive.getStockReceiveExpenses());
		}
		
		stockReceive.setGrandTotal(stockReceive.getTotal().subtract(new BigDecimal(stockReceive.getDiscountValue())));
		stockReceive.setNetTotal(stockReceive.getGrandTotal().add(stockReceive.getExpensesTotal()));
		
		inventoryRepository.saveAll(inventoriesForSave);
		inventoryTransactionRepository.saveAll(inventoryTransactionsForSave);
		stockReceiveItemRepository.saveAll(stockReceive.getStockReceiveItems());
		stockReceiveRepository.save(stockReceive);
	}
}
