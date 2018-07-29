package com.bartolay.inventory.development;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.Inventory;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.exceptions.SalesInvoiceException;
import com.bartolay.inventory.form.SalesInvoiceForm;
import com.bartolay.inventory.repositories.ClientRepository;
import com.bartolay.inventory.repositories.InventoryRepository;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.sales.entity.SalesInvoiceItem;
import com.bartolay.inventory.sales.repositories.CreditCardDetailsRepository;
import com.bartolay.inventory.sales.services.SalesInvoiceService;
import com.bartolay.inventory.services.InventoryCoreService;

@Component
@Transactional
public class _6SalesInvoiceBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	@Autowired
	private SalesInvoiceService salesInvoiceService;
	
	@Autowired
	private CreditCardDetailsRepository creditCardDetailsRepository;
	
	@Autowired
	private InventoryCoreService inventoryService;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public int getOrder() {
		return 6;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		SalesInvoiceForm salesInvoiceForm = new SalesInvoiceForm();
		salesInvoiceForm.setPaymentMethod(PaymentMethod.CREDITCARD);
		salesInvoiceForm.setCreditCardDetails(creditCardDetailsRepository.findById(1).get());
		salesInvoiceForm.setSalesPerson(userRepository.findByUsername("sales1"));
		salesInvoiceForm.setLocation(locationRepository.findById(1).get());
		salesInvoiceForm.setSalesInvoiceItems(getSalesInvoiceItems());
		salesInvoiceForm.setDiscountPercentage(new BigDecimal("3.5"));
		salesInvoiceForm.setTransactionDate(new Date());
		salesInvoiceForm.setYear("2018");
		salesInvoiceForm.setCustomer(userRepository.findById(1).get());
		
		try {
			salesInvoiceService.create(salesInvoiceForm);
		} catch (SalesInvoiceException e) {
			e.printStackTrace();
		}
	}
 
	private List<SalesInvoiceItem> getSalesInvoiceItems() {
		List<Inventory> inventories = inventoryRepository.findByLocation(locationRepository.findById(1).get());
		
		List<SalesInvoiceItem> salesInvoiceItems = new ArrayList<>();
		
		SalesInvoiceItem salesInvoiceItem = new SalesInvoiceItem();
		salesInvoiceItem.setInventory(inventories.get(0));
//		salesInvoiceItem.setItem(itemRepository.findById(1).get());
//		salesInvoiceItem.setUnit(itemRepository.findById(1).get().getDefaultUnit());
		salesInvoiceItem.setQuantity(new BigDecimal("2.211"));
		salesInvoiceItem.setUnitPrice(new BigDecimal("30.33"));
		salesInvoiceItems.add(salesInvoiceItem);
		
		salesInvoiceItem = new SalesInvoiceItem();
		salesInvoiceItem.setInventory(inventories.get(1));
//		salesInvoiceItem.setItem(itemRepository.findById(2).get());
//		salesInvoiceItem.setUnit(itemRepository.findById(2).get().getDefaultUnit());
		salesInvoiceItem.setQuantity(new BigDecimal("10"));
		salesInvoiceItem.setUnitPrice(new BigDecimal("50"));
		salesInvoiceItems.add(salesInvoiceItem);

		return salesInvoiceItems;
	}
}
