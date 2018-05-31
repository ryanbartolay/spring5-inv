package com.bartolay.inventory.development;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.repositories.ExpenseRepository;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.repositories.UnitRepository;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.services.InventoryCoreService;
import com.bartolay.inventory.stock.entity.StockReceive;
import com.bartolay.inventory.stock.entity.StockReceiveExpense;
import com.bartolay.inventory.stock.entity.StockReceiveItem;
import com.bartolay.inventory.stock.repositories.StockReceiveRepository;

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
	private StockReceiveRepository stockReceiveRepository;
	
	@Autowired
	private InventoryCoreService inventoryCoreService;
	
	@Autowired
	private UnitRepository unitRepository;
	
	private User user;
	
	@Override
	public int getOrder() {
		return 9;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		user = userRepository.findById(3).get();
		
		createStockReceives();
	}

	private void createStockReceives() {
		
		List<StockReceiveExpense> expenses = getExpenses();
		
		List<StockReceiveItem> items = getItems();
				
		StockReceive sr1 = new StockReceive();
		sr1.setStockReceiveItems(items);
		sr1.setExpenses(expenses);
		sr1.setDiscountValue(15);
		sr1.setCreatedBy(user);
		
		inventoryCoreService.createStockReceive(sr1);
		
		StockReceive sr2 = new StockReceive();
		sr2.setStockReceiveItems(items);
		sr2.setExpenses(null);
		sr2.setDiscountValue(5);
		sr2.setCreatedBy(user);
		
		inventoryCoreService.createStockReceive(sr2);
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
		item2.setCreatedBy(user);
		item2.setQuantity(new BigDecimal("50"));
		item2.setUnitCost(new BigDecimal("400"));
		item2.setUnit(unitRepository.findById(2).get());
		items.add(item2);
		
		StockReceiveItem item3 = new StockReceiveItem();
		item3.setItem(itemRepository.findById(1).get());
		item3.setCreatedBy(user);
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
		sre1.setCreatedBy(user);
		
		expenses.add(sre1);
		
		StockReceiveExpense sre2 = new StockReceiveExpense();
		sre2.setExpense(expenseRepository.findById(2).get());
		sre2.setAmount(new BigDecimal("100"));
		sre2.setCreatedBy(user);
		
		expenses.add(sre2);
		
		return expenses;
	}
}
