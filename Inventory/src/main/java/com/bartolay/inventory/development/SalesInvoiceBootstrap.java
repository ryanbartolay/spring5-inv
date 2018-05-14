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

import com.bartolay.inventory.entity.InventoryTransaction;
import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.sales.entity.CreditCardDetails;
import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.repositories.CreditCardDetailsRepository;
import com.bartolay.inventory.services.InventoryService;

@Component
@Transactional
public class SalesInvoiceBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

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
		
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 4, 21, 22, 33, 5);
		
		Item item = itemRepository.findById((long) 1).get();
		
		Set<InventoryTransaction> transactions = new HashSet<>();
		
		// inventory transaction
		InventoryTransaction inventoryTransaction = new InventoryTransaction();
		inventoryTransaction.setCreatedBy(user);
		inventoryTransaction.setItem(item);
		inventoryTransaction.setQuantity(new BigDecimal("2.211"));
		inventoryTransaction.setUnit(item.getDefaultUnit());
		inventoryTransaction.setUnitCost(new BigDecimal("30.33"));
		
		transactions.add(inventoryTransaction);
		
		System.err.println("------------------------fU");
		System.err.println(locationRepository.findById(1).get());
		System.err.println("------------------------fU");
		
		// sales invoice
		SalesInvoice salesInvoice = new SalesInvoice();
		salesInvoice.setCreditCardDetails(ccDetails);
		salesInvoice.setPaymentMethod(PaymentMethod.CREDITCARD);
		salesInvoice.setDocumentNumber("Sales#12344");
		salesInvoice.setYear("2018");
		salesInvoice.setTransactionDate(cal.getTime());
		salesInvoice.setSalesPerson(salesPerson);
		salesInvoice.setCreatedBy(user);
		salesInvoice.setLocation(locationRepository.findById(1).get());
		salesInvoice.setIntentoryTransactions(transactions);
		
		System.err.println("creating sales invoice");
		
		inventoryService.createSalesInvoice(salesInvoice);
		
//		SalesInvoiceForm salesForm = new SalesInvoiceForm();
//		salesForm.setPaymentMethod(PaymentMethod.CREDITCARD);
//		salesForm.setCreditCardDetails(ccDetails);
//		salesForm.setDocumentNumber("Sales#12344");
//		salesForm.setYear("2018");
//		salesForm.setTransactionDate(CalendarUtils.dateToString(cal.getTime()));
//		salesForm.setSalesPerson(sales1);
//		salesForm.setCreatedBy(user);
//		salesForm.setLocation(locationRepository.findById(1).get());
//		
//		salesInvoiceService.create(salesForm);
//		
//		cal.set(2018, 5, 21, 22, 33, 5);
//		
//		SalesInvoiceForm salesForm2 = new SalesInvoiceForm();
//		salesForm2.setPaymentMethod(PaymentMethod.CASH);
//		salesForm2.setDocumentNumber("Sales#12aas34");
//		salesForm2.setYear("2018");
//		salesForm2.setTransactionDate(CalendarUtils.dateToString(cal.getTime()));
//		salesForm2.setSalesPerson(sales2);
//		salesForm2.setCreatedBy(user);
//		salesForm2.setLocation(locationRepository.findById(2).get());
//		
//		salesInvoiceService.create(salesForm2);
	}
}
