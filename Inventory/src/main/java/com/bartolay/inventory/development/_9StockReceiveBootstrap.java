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

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.exceptions.StockReceiveException;
import com.bartolay.inventory.form.StockReceiveForm;
import com.bartolay.inventory.repositories.ExpenseRepository;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.repositories.SupplierRepository;
import com.bartolay.inventory.repositories.UnitRepository;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.sales.repositories.CreditCardDetailsRepository;
import com.bartolay.inventory.stock.entity.StockReceiveExpense;
import com.bartolay.inventory.stock.entity.StockReceiveItem;
import com.bartolay.inventory.stock.services.StockReceiveService;

@Component
@Transactional
public class _9StockReceiveBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	@Autowired
	private UserRepository userRepository;

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
	private StockReceiveService stockReceiveService;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private CreditCardDetailsRepository creditCardDetailsRepository;

	@Override
	public int getOrder() {
		return 9;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		user = userRepository.findById(3).get();

		try {
			createStockReceives();
		} catch (StockReceiveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createStockReceives() throws StockReceiveException {

		List<StockReceiveExpense> expenses = getExpenses();

		List<StockReceiveItem> items = getItems();
		
		List<StockReceiveItem> item2 = getItems();
		List<StockReceiveItem> item3 = getItems();

		StockReceiveForm form = new StockReceiveForm();
		form.setPaymentMethod(PaymentMethod.CASH);
		form.setLocation(locationRepository.findById(1).get());
		form.setYear("2018");
		form.setTransactionDate(new Date());
		form.setStockReceiveItems(items);
		form.setExpenses(expenses);
		form.setDiscountValue(15);
		form.setSupplier(supplierRepository.findById(1).get());

		stockReceiveService.create(form);
		
		form = new StockReceiveForm();
		form.setPaymentMethod(PaymentMethod.CHECK);
		form.setLocation(locationRepository.findById(2).get());
		form.setYear("2018");
		form.setTransactionDate(new Date());
		form.setStockReceiveItems(item2);
		form.setExpenses(null);
		form.setDiscountValue(5);
		form.setSupplier(supplierRepository.findById(2).get());

		stockReceiveService.create(form);
		
		form = new StockReceiveForm();
		form.setPaymentMethod(PaymentMethod.CREDITCARD);
		form.setLocation(locationRepository.findById(2).get());
		form.setYear("2018");
		form.setTransactionDate(new Date());
		form.setStockReceiveItems(item3);
		form.setExpenses(null);
		form.setDiscountValue(5);
		form.setSupplier(supplierRepository.findById(1).get());
		form.setCreditCardDetails(creditCardDetailsRepository.findById(1).get());

		stockReceiveService.create(form);
	}

	private List<StockReceiveItem> getItems() {
		List<StockReceiveItem> items = new ArrayList<>();

		StockReceiveItem item = new StockReceiveItem();
		item.setItem(itemRepository.findById(2).get());
		item.setCreatedBy(user);
		item.setQuantity(new BigDecimal("23"));
		item.setUnitCost(new BigDecimal("100"));
		item.setUnit(unitRepository.findById(2).get());
		items.add(item);

		StockReceiveItem item2 = new StockReceiveItem();
		item2.setItem(itemRepository.findById(1).get());
		item2.setQuantity(new BigDecimal("50"));
		item2.setUnitCost(new BigDecimal("400"));
		item2.setUnit(unitRepository.findById(2).get());
		items.add(item2);

		StockReceiveItem item3 = new StockReceiveItem();
		item3.setItem(itemRepository.findById(1).get());
		item3.setQuantity(new BigDecimal("5"));
		item3.setUnitCost(new BigDecimal("10"));
		item3.setUnit(unitRepository.findById(1).get());
		items.add(item3);

		return items;
	}

	private List<StockReceiveExpense> getExpenses() {
		List<StockReceiveExpense> expenses = new ArrayList<>();

		StockReceiveExpense sre1 = new StockReceiveExpense();
		sre1.setExpense(expenseRepository.findById(1).get());
		sre1.setAmount(new BigDecimal("60.10"));

		expenses.add(sre1);

		StockReceiveExpense sre2 = new StockReceiveExpense();
		sre2.setExpense(expenseRepository.findById(2).get());
		sre2.setAmount(new BigDecimal("100"));

		expenses.add(sre2);

		return expenses;
	}
}
