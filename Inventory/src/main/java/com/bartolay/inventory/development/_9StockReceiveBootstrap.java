package com.bartolay.inventory.development;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.exceptions.StockReceivedException;
import com.bartolay.inventory.form.StockReceivedForm;
import com.bartolay.inventory.repositories.CurrencyRepository;
import com.bartolay.inventory.repositories.ExpenseRepository;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.repositories.SupplierRepository;
import com.bartolay.inventory.repositories.UnitRepository;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.sales.repositories.CreditCardDetailsRepository;
import com.bartolay.inventory.services.ExpenseService;
import com.bartolay.inventory.stock.entity.StockReceivedExpense;
import com.bartolay.inventory.stock.entity.StockReceivedItem;
import com.bartolay.inventory.stock.services.StockReceivedService;

@Component
@Transactional
public class _9StockReceiveBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private UnitRepository unitRepository;

	private User user;

	@Autowired
	private StockReceivedService stockReceiveService;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private CreditCardDetailsRepository creditCardDetailsRepository;
	
	@Autowired
	private DateFormat dateFormat;
	
	@Autowired
	private CurrencyRepository currencyRepository;

	@Override
	public int getOrder() {
		return 9;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		user = userRepository.findById(3).get();

		try {
			createStockReceives();
		} catch (StockReceivedException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createStockReceives() throws StockReceivedException, ParseException {

		List<StockReceivedExpense> expenses = getExpenses();

		List<StockReceivedItem> items = getItems();
		
		List<StockReceivedItem> item2 = getItems2();
		List<StockReceivedItem> item3 = getItems();

		StockReceivedForm form = new StockReceivedForm();
		form.setPaymentMethod(PaymentMethod.CASH);
		form.setLocation(locationRepository.findById(1).get());
		form.setYear("2018");
		form.setTransactionDate(dateFormat.format(new Date()));
		form.setStockReceiveItems(items);
		form.setExpenses(expenses);
		form.setDiscountValue(15);
		form.setSupplier(supplierRepository.findById(1).get());

		stockReceiveService.create(form);
		
		form = new StockReceivedForm();
		form.setPaymentMethod(PaymentMethod.CHECK);
		form.setLocation(locationRepository.findById(2).get());
		form.setDescription("Check payment with a balance of 2.20");
		form.setYear("2018");
		form.setTransactionDate(dateFormat.format(new Date()));
		form.setStockReceiveItems(item2);
		form.setCurrency(currencyRepository.findById(2).get());
		form.setExpenses(null);
		form.setDiscountValue(5);
		form.setSupplier(supplierRepository.findById(2).get());

		stockReceiveService.create(form);
		
		form = new StockReceivedForm();
		form.setPaymentMethod(PaymentMethod.CREDITCARD);
		form.setLocation(locationRepository.findById(2).get());
		form.setDocument_number("DT-22123");	
		form.setYear("2018");
		form.setTransactionDate(dateFormat.format(new Date()));
		form.setStockReceiveItems(item3);
		form.setExpenses(null);
		form.setDiscountValue(5);
		form.setSupplier(supplierRepository.findById(1).get());
		form.setCreditCardDetails(creditCardDetailsRepository.findById(1).get());

		stockReceiveService.create(form);
	}

	private List<StockReceivedItem> getItems() {
		List<StockReceivedItem> items = new ArrayList<>();

		StockReceivedItem item = new StockReceivedItem();
		item.setItem(itemRepository.findById(2).get());
		item.setCreatedBy(user);
		item.setQuantity(new BigDecimal("23"));
		item.setUnitCost(new BigDecimal("100"));
		item.setUnit(unitRepository.findById(2).get());
		items.add(item);

		StockReceivedItem item2 = new StockReceivedItem();
		item2.setItem(itemRepository.findById(1).get());
		item2.setQuantity(new BigDecimal("50"));
		item2.setUnitCost(new BigDecimal("400"));
		item2.setUnit(unitRepository.findById(2).get());
		items.add(item2);

		StockReceivedItem item3 = new StockReceivedItem();
		item3.setItem(itemRepository.findById(1).get());
		item3.setQuantity(new BigDecimal("5"));
		item3.setUnitCost(new BigDecimal("10"));
		item3.setUnit(unitRepository.findById(1).get());
		items.add(item3);

		return items;
	}
	
	private List<StockReceivedItem> getItems2() {
		List<StockReceivedItem> items = new ArrayList<>();

		StockReceivedItem item = new StockReceivedItem();
		item.setItem(itemRepository.findById(3).get());
		item.setCreatedBy(user);
		item.setQuantity(new BigDecimal("200"));
		item.setUnitCost(new BigDecimal("90.72"));
		item.setUnit(unitRepository.findById(2).get());
		items.add(item);

		StockReceivedItem item3 = new StockReceivedItem();
		item3.setItem(itemRepository.findById(1).get());
		item3.setQuantity(new BigDecimal("50"));
		item3.setUnitCost(new BigDecimal("100"));
		item3.setUnit(unitRepository.findById(1).get());
		items.add(item3);

		return items;
	}

	private List<StockReceivedExpense> getExpenses() {
		List<StockReceivedExpense> expenses = new ArrayList<>();

		StockReceivedExpense sre1 = new StockReceivedExpense();
		sre1.setExpense(expenseRepository.findById(1).get());
		sre1.setAmount(new BigDecimal("60.10"));

		expenses.add(sre1);

		StockReceivedExpense sre2 = new StockReceivedExpense();
		sre2.setExpense(expenseRepository.findById(2).get());
		sre2.setAmount(new BigDecimal("100"));

		expenses.add(sre2);

		return expenses;
	}
}
