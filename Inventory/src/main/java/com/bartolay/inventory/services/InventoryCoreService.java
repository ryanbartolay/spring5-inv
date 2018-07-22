package com.bartolay.inventory.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bartolay.inventory.entity.Inventory;
import com.bartolay.inventory.entity.InventoryTransaction;
import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.enums.SalesInvoiceStatus;
import com.bartolay.inventory.enums.Status;
import com.bartolay.inventory.enums.StockAdjustmentType;
import com.bartolay.inventory.enums.TransactionType;
import com.bartolay.inventory.exceptions.SalesInvoiceException;
import com.bartolay.inventory.exceptions.SalesReturnException;
import com.bartolay.inventory.exceptions.StockAdjustmentException;
import com.bartolay.inventory.exceptions.StockTransferException;
import com.bartolay.inventory.repositories.InventoryRepository;
import com.bartolay.inventory.repositories.InventoryTransactionRepository;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.sales.entity.CreditCardDetails;
import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.entity.SalesInvoiceItem;
import com.bartolay.inventory.sales.entity.SalesReturn;
import com.bartolay.inventory.sales.entity.SalesReturnItem;
import com.bartolay.inventory.sales.repositories.CreditCardDetailsRepository;
import com.bartolay.inventory.sales.repositories.SalesInvoiceItemRepository;
import com.bartolay.inventory.sales.repositories.SalesInvoiceRepository;
import com.bartolay.inventory.sales.repositories.SalesReturnItemRepository;
import com.bartolay.inventory.sales.repositories.SalesReturnRepository;
import com.bartolay.inventory.stock.entity.StockAdjustment;
import com.bartolay.inventory.stock.entity.StockAdjustmentItem;
import com.bartolay.inventory.stock.entity.StockOpening;
import com.bartolay.inventory.stock.entity.StockOpeningItem;
import com.bartolay.inventory.stock.entity.StockReceived;
import com.bartolay.inventory.stock.entity.StockReceivedExpense;
import com.bartolay.inventory.stock.entity.StockReceivedItem;
import com.bartolay.inventory.stock.entity.StockTransfer;
import com.bartolay.inventory.stock.entity.StockTransferItem;
import com.bartolay.inventory.stock.repositories.StockAdjustmentItemRepository;
import com.bartolay.inventory.stock.repositories.StockAdjustmentRepository;
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
@Transactional
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
	private SalesInvoiceRepository salesInvoiceRepository;
	@Autowired
	private SalesInvoiceItemRepository salesInvoiceItemRepository; 
	@Autowired
	private StockAdjustmentRepository stockAdjustmentRepository;
	@Autowired
	private StockAdjustmentItemRepository stockAdjustmentItemRepository;
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
	private SalesReturnRepository salesReturnRepository;
	@Autowired
	private SalesReturnItemRepository salesReturnItemRepository;
	@Autowired
	private StockTransferRepository stockTransferRepository;
	@Autowired
	private StockTransferItemRepository stockTransferItemRepository;
	@Autowired
	private ItemRepository itemRepository;
	/**
	 * Creates the Stock Opening
	 * @param stockOpening
	 */
	@Transactional
	public StockOpening createStockOpening(StockOpening stockOpening) {

		System.err.println(stockOpening.getItems());

		// Lets save the stock opening first so we can generate a system number
		stockOpening.setStatus(Status.SUCCESS);
		stockOpening.setCreatedBy(userCredentials.getLoggedInUser());
		stockOpening.setTotal(new BigDecimal("0"));
		stockOpening = stockOpeningRepository.save(stockOpening);

		List<Inventory> inventories = inventoryRepository.findByLocation(stockOpening.getLocation());
		List<InventoryTransaction> invTransactions = new ArrayList<>();
		List<Inventory> inventoriesForSave = new ArrayList<>();

		// iterate through items and check if default unit
		for(StockOpeningItem stockOpeningItem : stockOpening.getItems()) {

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
			System.err.println(inventory.getQuantity() + " " + stockOpeningItem.getQuantity());
			inventory.setQuantity(inventory.getQuantity().add(stockOpeningItem.getQuantity()));
			inventory.setUnitCost(stockOpeningItem.getUnitCost());
			inventoryTransaction.setRateQuantity(stockOpeningItem.getQuantity());

			inventoryTransaction.setQuantityAfter(inventory.getQuantity());
			inventoryTransaction.setItem(stockOpeningItem.getItem());
			inventoryTransaction.setLocation(stockOpening.getLocation());
			inventoryTransaction.setTransactionSystemNumber(stockOpening.getSystemNumber());
			
			inventoryTransaction.setUnit(stockOpeningItem.getUnit());
			//inventoryTransaction.setUnitCostBefore(new BigDecimal("0"));
			inventoryTransaction.setUnitCostAfter(stockOpeningItem.getUnitCost());
			inventoryTransaction.setTransactionType(TransactionType.STOCK_OPENING);
			inventoryTransaction.setCreatedBy(userCredentials.getLoggedInUser());

			stockOpeningItem.setRateQuantity(inventoryTransaction.getRateQuantity());
			stockOpeningItem.setUnitCostTotal(stockOpeningItem.getUnitCost().multiply(stockOpeningItem.getRateQuantity()));
			stockOpeningItem.setStatus(Status.SUCCESS);

			stockOpening.setTotal(stockOpening.getTotal().add(stockOpeningItem.getUnitCostTotal()));

			inventoryTransaction.setInventory(inventory);

			invTransactions.add(inventoryTransaction);
			inventoriesForSave.add(inventory);
		}

		stockOpeningItemRepository.saveAll(stockOpening.getItems());

		inventoryRepository.saveAll(inventoriesForSave);
		inventoryTransactionRepository.saveAll(invTransactions);

		stockOpening = stockOpeningRepository.save(stockOpening);

		return stockOpening;
	}


	/**
	 * This creates a Sales Invoice,
	 * - will create and invoice if, 
	 * 		- the item inventory exists
	 * 		- quantity is less than the inventory quantity
	 * @param salesInvoice
	 */
	@Transactional
	public SalesInvoice createSalesInvoice(SalesInvoice salesInvoice) throws SalesInvoiceException {
		// TODO should have sales invoice item

		// Lets save the sales invoice first so we can generate a system number
		// this will be essential in case theres a wrong data on the sales invoice
		salesInvoice.setCreatedBy(userCredentials.getLoggedInUser());
		salesInvoice.setSubtotal(new BigDecimal("0"));
		salesInvoice.setDiscountTotal(new BigDecimal("0"));
		salesInvoice.setNetTotal(new BigDecimal("0"));
		salesInvoice.setSalesInvoiceStatus(SalesInvoiceStatus.DRAFT);

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

		for(SalesInvoiceItem salesInvoiceItem : salesInvoice.getSalesInvoiceItems()) {

			salesInvoiceItem.setCreatedBy(userCredentials.getLoggedInUser());
			salesInvoiceItem.setSalesInvoice(salesInvoice);

			Inventory inventory = inventoryUtility.findInventoryFromList(inventories, salesInvoice.getLocation(), salesInvoiceItem.getItem(), salesInvoiceItem.getUnit());

			// we will only process what is existing in the inventory (you cannot put sales invoice with inventory not existing, lol)
			if(inventory == null) {
				throw new SalesInvoiceException("Item " + salesInvoiceItem.getItem().getName() + " is not found in "
						+ "Location " + salesInvoice.getLocation().getName());
			}

			BigDecimal after_quantity = inventory.getQuantity().subtract(salesInvoiceItem.getQuantity());

			// we cannot process the sales if more than the current inventory
			if(after_quantity.doubleValue() <= 0) {
				throw new SalesInvoiceException("Item "+ salesInvoiceItem.getItem().getName() +" "
						+ "Quantity "+ salesInvoiceItem.getQuantity() +" is greater than the "
						+ "current inventory "+ inventory.getQuantity() +".");
			}

			inventory.setQuantity(after_quantity);
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
			inventoryTransaction.setRateQuantity(salesInvoiceItem.getQuantity());
			inventoryTransaction.setUnitCostAfter(salesInvoiceItem.getUnitPrice());
			inventoryTransaction.setQuantityBefore(inventory.getQuantity());
			inventoryTransaction.setQuantityAfter(after_quantity);

			salesInvoiceItem.setUnitPriceTotal(salesInvoiceItem.getUnitPrice().multiply(salesInvoiceItem.getQuantity()));
			salesInvoiceItem.setStatus(Status.SUCCESS);

			salesInvoice.setSubtotal(salesInvoice.getSubtotal().add(salesInvoiceItem.getUnitPriceTotal()));
			salesInvoice.setDiscountTotal(salesInvoice.getSubtotal().multiply(salesInvoice.getDiscountPercentage().divide(new BigDecimal("100"))));
			salesInvoice.setNetTotal(salesInvoice.getSubtotal().subtract(salesInvoice.getDiscountTotal()));

			inventoryTransaction.setQuantityAfter(inventory.getQuantity());

			salesInvoiceItemRepository.save(salesInvoiceItem);

			inventoryTransaction.setItem(salesInvoiceItem.getItem());

			inventoriesForSave.add(inventory);
			inventoryTransactions.add(inventoryTransaction);
		}

		inventoryTransactionRepository.saveAll(inventoryTransactions);
		inventoryRepository.saveAll(inventoriesForSave);

		return salesInvoiceRepository.save(salesInvoice);
	}

	@Transactional
	public void cancelSalesInvoice(SalesInvoice salesInvoice) {

		final SalesInvoice updatedSalesInvoice = salesInvoiceRepository.findById(salesInvoice.getSystemNumber()).get();

		List<SalesInvoiceItem> salesInvoiceItems = salesInvoiceItemRepository.findBySalesInvoice(updatedSalesInvoice);

		List<Inventory> inventories = new ArrayList<>(); 
		List<InventoryTransaction> inventoryTransactions = inventoryTransactionRepository.findByTransactionSystemNumberAndTransactionType(updatedSalesInvoice.getSystemNumber(), TransactionType.SALES_INVOICE); 

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
				cancelledTransaction.setUnitCostAfter(salesInvoiceItem.getUnitPrice());
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

		updatedSalesInvoice.setSalesInvoiceStatus(SalesInvoiceStatus.CANCELLED);

		salesInvoiceRepository.save(updatedSalesInvoice);
		salesInvoiceItemRepository.saveAll(salesInvoiceItems);
		inventoryRepository.saveAll(inventories);
		inventoryTransactionRepository.saveAll(inventoryTransactions);

	}

	@Transactional(rollbackFor=Exception.class)
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
				throw new StockTransferException("Stock "+ stockTransferItem.getItem().getId() +" with unit "+ stockTransferItem.getUnit().getId() +" not found from source location. " + stockTransfer.getFromLocation().getId());
			}

			inventoryTransaction.setInventory(fromInventory);

			// we also need to check if the TO already exists
			Inventory toInventory = inventoryUtility.findInventoryFromList(
					toInventories, 
					stockTransfer.getToLocation().getId(), 
					stockTransferItem.getItem().getId(),
					stockTransferItem.getUnit().getId());

			if(toInventory == null) {
				toInventory = new Inventory();
				toInventory.setItem(stockTransferItem.getItem());
				toInventory.setLocation(stockTransfer.getToLocation());
				toInventory.setUnit(stockTransferItem.getUnit());
				toInventory.setQuantity(new BigDecimal("0"));
				toInventory.setCreatedBy(userCredentials.getLoggedInUser());
				toInventory.setUnitCost(fromInventory.getUnitCost());
			}

			stockTransferItem.setUnitCost(toInventory.getUnitCost());
			
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
			System.err.println("from1");
			System.err.println(fromInventory);
			System.err.println("to1");
			System.err.println(toInventory);

			fromInventory.setQuantity(newFromQuantity);
			toInventory.setQuantity(toInventory.getQuantity().add(stockTransferItem.getQuantity()));
			inventoryTransaction.setRateQuantity(stockTransferItem.getQuantity());

			// after transaction
			jsonObj.put("from_quantity", fromInventory.getQuantity());
			jsonObj.put("to_quantity", toInventory.getQuantity());

			inventoryTransaction.setTransactionAfter(jsonObj.toString());

			System.err.println("from2");
			System.err.println(fromInventory);
			System.err.println("to2");
			System.err.println(toInventory);
			
			inventoryTransactions.add(inventoryTransaction);
			inventoriesForSave.add(fromInventory);
			inventoriesForSave.add(toInventory);
		}

		System.err.println("ssss");
		
		for(StockTransferItem i : stockTransfer.getStockTransferItems()) {
			System.err.println(i);	
		}
		
		stockTransferItemRepository.saveAll(stockTransfer.getStockTransferItems());
		System.err.println("aaaa");
		inventoryTransactionRepository.saveAll(inventoryTransactions);
		System.err.println("bbbb");
		
		for(Inventory i : inventoriesForSave) {
			System.err.println(i);
		}
		inventoryRepository.saveAll(inventoriesForSave);
		System.err.println("cccc");
//		return stockTransfer;
	}

	@Transactional(rollbackFor=Exception.class)
	public StockReceived createStockReceive(StockReceived stockReceive) {

		// lets generate systemNumber by saving the stockreceive
		stockReceive.setCreatedBy(userCredentials.getLoggedInUser());
		stockReceive.setExpensesTotal(new BigDecimal("0"));
		stockReceive.setTotal(new BigDecimal("0"));
		stockReceive.setGrandTotal(new BigDecimal("0"));
		stockReceive.setNetTotal(new BigDecimal("0"));
		stockReceive = stockReceiveRepository.save(stockReceive);
		
		/**
		 * @author rhaynel
		 * @since 2018-07-19
		 * 
		*/
		if(!stockReceive.getPaymentMethod().equals(PaymentMethod.CREDITCARD)) {
			stockReceive.setCreditCardDetails(null);
		}else {
			CreditCardDetails cardDetails = stockReceive.getCreditCardDetails();
			cardDetails.setCreatedBy(userCredentials.getLoggedInUser());
			creditCardDetailsRepository.save(cardDetails);
		}
		
		// expenses
		if(stockReceive.getStockReceiveExpenses() != null) {
			for(StockReceivedExpense expense : stockReceive.getStockReceiveExpenses()) {
				if(expense != null) {
					expense.setStockReceive(stockReceive);
					expense.setCreatedBy(userCredentials.getLoggedInUser());
					
					/**
					 * @author rhaynel
					 * @since 2018-07-19
					 * handle request with invalid data from form request 
					 * examp [Expene[id=null, amount=null], Expene[id=1, amount=10]]
					 * this occur when user manipulate expenses by adding and removing, sometimes array starts at 1 not in 0
					*/
					
					BigDecimal expAmount =expense.getAmount()==null ? new BigDecimal(0) : expense.getAmount();
					stockReceive.setExpensesTotal(stockReceive.getExpensesTotal().add(expAmount));
				}
			}
		}

		List<Inventory> inventories = inventoryRepository.findByLocation(stockReceive.getLocation());
		List<Inventory> inventoriesForSave = new ArrayList<>();
		List<InventoryTransaction> inventoryTransactionsForSave = new ArrayList<>();

		for(StockReceivedItem stockReceiveItem : stockReceive.getStockReceiveItems()) {
			
			
			stockReceiveItem.setStockReceive(stockReceive);
			stockReceiveItem.setCreatedBy(userCredentials.getLoggedInUser());
			
			Item item = itemRepository.findById(stockReceiveItem.getItem().getId()).get();
			
			Inventory inventory = inventoryUtility.findInventoryFromList(inventories, stockReceive.getLocation(), item, stockReceiveItem.getUnit());

			if(inventory == null) {
				inventory = new Inventory();
				inventory.setLocation(stockReceive.getLocation());
				inventory.setItem(item);
				inventory.setUnit(stockReceiveItem.getUnit());
				inventory.setQuantity(new BigDecimal("0"));
				inventory.setCreatedBy(userCredentials.getLoggedInUser());
				inventory.setUnitCost(stockReceiveItem.getUnitCost());
			} else {
				inventory.setUpdatedBy(userCredentials.getLoggedInUser());
			}

			// inventory transaction
			InventoryTransaction inventoryTransaction = new InventoryTransaction();
			inventoryTransaction.setItem(item);
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
			for (StockReceivedExpense expense : stockReceive.getStockReceiveExpenses()) {
				/**
				 * @author rhaynel
				 * @since 2018-07-19
				 * handle request with invalid data from form request 
				 * examp [Expene[id=null, amount=null], Expene[id=1, amount=10]]
				 * this occur when user manipulate expenses by adding and removing, sometimes array starts at 1 not in 0
				*/
				if(expense.getAmount() != null) {
					stockReceiveExpenseRepository.save(expense);
				}
				
			}
		}

		stockReceive.setGrandTotal(stockReceive.getTotal().subtract(new BigDecimal(stockReceive.getDiscountValue())));
		stockReceive.setNetTotal(stockReceive.getGrandTotal().add(stockReceive.getExpensesTotal()));
		
		inventoryRepository.saveAll(inventoriesForSave);
		inventoryTransactionRepository.saveAll(inventoryTransactionsForSave);
		stockReceiveItemRepository.saveAll(stockReceive.getStockReceiveItems());
		return stockReceiveRepository.save(stockReceive);
	}

	@Transactional(rollbackFor=Exception.class)
	public void createSalesReturn(SalesReturn salesReturn) throws SalesReturnException, SalesInvoiceException {

		final SalesInvoice salesInvoice = salesInvoiceRepository.findById(salesReturn.getSalesInvoice().getSystemNumber()).get();

		salesReturn = salesReturnRepository.save(salesReturn);
		
		if(salesInvoice == null) {
			throw new SalesReturnException("Sales Invoice not found");
		}
		
		List<SalesInvoiceItem> items = salesInvoice.getSalesInvoiceItems();

		List<Inventory> inventories = inventoryRepository.findByLocation(salesInvoice.getLocation());

		List<SalesInvoiceItem> salesInvoiceItemsForSave = new ArrayList<>();
		List<Inventory> inventoriesForSave = new ArrayList<>();
		List<InventoryTransaction> inventoryTransactionsForSave = new ArrayList<>();

		for(SalesReturnItem salesReturnItem : salesReturn.getSalesReturnItems()) {

			if(items.contains(salesReturnItem.getSalesInvoiceItem())) {
				
				salesReturnItem.setSalesReturn(salesReturn);
				
				SalesInvoiceItem salesInvoiceItem = items.get(items.indexOf(salesReturnItem.getSalesInvoiceItem()));
				
				SalesInvoiceItem tx_SalesInvoiceItem = inventoryUtility.subtractQuantitySalesInvoiceItem(salesInvoiceItem, salesReturnItem.getQuantity());
				
				System.err.println("tx_SalesInvoiceItem");
				System.err.println(tx_SalesInvoiceItem);
				
				Inventory inventory = inventoryUtility.findInventoryFromList(inventories, salesInvoice.getLocation(), salesInvoiceItem.getItem(), salesInvoiceItem.getUnit());
				
				// inventory transaction
				InventoryTransaction inventoryTransaction = new InventoryTransaction();
				inventoryTransaction.setItem(salesInvoiceItem.getItem());
				inventoryTransaction.setLocation(salesInvoice.getLocation());
				inventoryTransaction.setUnit(salesInvoiceItem.getUnit());
//				inventoryTransaction.setUnitCost(salesRet);
				inventoryTransaction.setTransactionType(TransactionType.SALES_RETURN);
				inventoryTransaction.setRawQuantity(salesReturnItem.getQuantity());
				inventoryTransaction.setTransactionSystemNumber(salesInvoice.getSystemNumber());
				inventoryTransaction.setCreatedBy(userCredentials.getLoggedInUser());
				inventoryTransaction.setRateQuantity(salesInvoiceItem.getQuantity());
				inventoryTransaction.setInventory(inventory);

				inventoryTransaction.setQuantityBefore(inventory.getQuantity());
				// increment quantity
				inventory.setQuantity(inventory.getQuantity().add(salesReturnItem.getQuantity()));

				inventoryTransaction.setQuantityAfter(inventory.getQuantity());

				salesReturnItem.setCreatedBy(userCredentials.getLoggedInUser());
				salesInvoiceItemsForSave.add(tx_SalesInvoiceItem);
				inventoryTransactionsForSave.add(inventoryTransaction);
				inventoriesForSave.add(inventory);
			} else {
				throw new SalesReturnException("Item is not found");
			}
		}

		
		salesReturnItemRepository.saveAll(salesReturn.getSalesReturnItems());
		salesInvoiceItemRepository.saveAll(salesInvoiceItemsForSave);
		inventoryTransactionRepository.saveAll(inventoryTransactionsForSave);
		inventoryRepository.saveAll(inventoriesForSave);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void createStockAdjustment(StockAdjustment stockAdjustment) throws StockAdjustmentException {
		
		StockAdjustmentType adjustmentType = null;
		stockAdjustment.setCreatedBy(userCredentials.getLoggedInUser());
		
		try {
			adjustmentType = StockAdjustmentType.valueOf(stockAdjustment.getStockAdjustmentType().toUpperCase());
			if(adjustmentType == null) {
				throw new StockAdjustmentException("Wrong value for type " + stockAdjustment.getStockAdjustmentType());
			}
		} catch(Exception e) {
			throw new StockAdjustmentException("Wrong value for type " + stockAdjustment.getStockAdjustmentType());
		}

		
		
		List<Inventory> inventories = inventoryRepository.findByLocation(stockAdjustment.getLocation());
		List<Inventory> inventoriesForSave = new ArrayList<>();
		List<InventoryTransaction> inventoryTransactionsForSave = new ArrayList<>();
		
		List<StockAdjustmentItem> stockAdjustmentItems = stockAdjustment.getStockAdjustmentItems();
		
		stockAdjustment = stockAdjustmentRepository.save(stockAdjustment);
		
		for(StockAdjustmentItem stockAdjustmentItem : stockAdjustmentItems) {
			
			if(inventories.contains(stockAdjustmentItem.getInventory())) {
				
				Inventory inventory = inventories.get(inventories.indexOf(stockAdjustmentItem.getInventory()));
				
				
				// inventory transaction
				InventoryTransaction inventoryTransaction = new InventoryTransaction();
				inventoryTransaction.setItem(stockAdjustmentItem.getInventory().getItem());
				inventoryTransaction.setLocation(stockAdjustment.getLocation());
				inventoryTransaction.setUnit(stockAdjustmentItem.getInventory().getUnit());
				inventoryTransaction.setTransactionType(TransactionType.STOCK_ADJUSTMENT);
				inventoryTransaction.setRawQuantity(stockAdjustmentItem.getQuantity());
				inventoryTransaction.setTransactionSystemNumber(stockAdjustment.getSystemNumber());
				inventoryTransaction.setCreatedBy(userCredentials.getLoggedInUser());
				inventoryTransaction.setRateQuantity(stockAdjustmentItem.getQuantity());
				inventoryTransaction.setInventory(inventory);
				
				switch(adjustmentType) {
				case QUANTITY:
					inventoryTransaction.setQuantityBefore(inventory.getQuantity());
					inventory.setQuantity(stockAdjustmentItem.getCost());
					inventoryTransaction.setQuantityAfter(inventory.getQuantity());
					break;
				case VALUE:
					inventoryTransaction.setUnitCostBefore(inventory.getQuantity());
					inventory.setUnitCost(stockAdjustmentItem.getCost());
					inventoryTransaction.setUnitCostAfter(inventory.getQuantity());
					break;				
				}
				
				inventoryTransactionsForSave.add(inventoryTransaction);
				stockAdjustmentItem.setStockAdjustment(stockAdjustment);
				inventoriesForSave.add(inventory);
			} else {
				throw new StockAdjustmentException(stockAdjustmentItem.getInventory().getItem().getName() + " does not exists in " + stockAdjustment.getLocation().getName());
			}
		}
		
		inventoryTransactionRepository.saveAll(inventoryTransactionsForSave);
		inventoryRepository.saveAll(inventoriesForSave);
		stockAdjustmentItemRepository.saveAll(stockAdjustmentItems);
		stockAdjustmentRepository.save(stockAdjustment);
	}
}
