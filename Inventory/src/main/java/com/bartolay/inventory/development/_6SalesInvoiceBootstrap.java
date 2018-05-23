package com.bartolay.inventory.development;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.sales.entity.CreditCardDetails;
import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.entity.SalesInvoiceItem;
import com.bartolay.inventory.sales.repositories.CreditCardDetailsRepository;
import com.bartolay.inventory.services.InventoryService;

@Component
@Transactional
public class _6SalesInvoiceBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	@Autowired
	private CreditCardDetailsRepository creditCardDetailsRepository;
	
	@Autowired
	private InventoryService inventoryService;
	
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
		CreditCardDetails ccDetails = creditCardDetailsRepository.findById(1).get();
		User user = userRepository.findByUsername("admin");
		User salesPerson = userRepository.findByUsername("sales1");
		
		User customer = userRepository.findByUsername("customer");
		
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 4, 21, 22, 33, 5);
		
		Item item = itemRepository.findById(1).get();
		
		Set<SalesInvoiceItem> salesInvoiceItems = new HashSet<>();
		
		SalesInvoiceItem salesInvoiceItem = new SalesInvoiceItem();
		salesInvoiceItem.setCreatedBy(user);
		salesInvoiceItem.setItem(item);
		salesInvoiceItem.setQuantity(new BigDecimal("2.211"));
		salesInvoiceItem.setUnit(item.getDefaultUnit());
		salesInvoiceItem.setUnitCost(new BigDecimal("30.33"));
		salesInvoiceItems.add(salesInvoiceItem);
		
		System.err.println(locationRepository.findById(1).get());
		
		// sales invoice
		SalesInvoice salesInvoice = new SalesInvoice();
		salesInvoice.setCreditCardDetails(ccDetails);
		salesInvoice.setPaymentMethod(PaymentMethod.CREDITCARD);
		salesInvoice.setDocumentNumber("Sales#12344");
		salesInvoice.setYear("2018");
		salesInvoice.setTransactionDate(cal.getTime());
		salesInvoice.setSalesPerson(salesPerson);
		salesInvoice.setCustomer(customer);
		salesInvoice.setCreatedBy(user);
		salesInvoice.setLocation(locationRepository.findById(1).get());
		salesInvoice.setSalesInvoiceItems(salesInvoiceItems);
		
		System.err.println("creating sales invoice");
		
		inventoryService.createSalesInvoice(salesInvoice);
	}
}
