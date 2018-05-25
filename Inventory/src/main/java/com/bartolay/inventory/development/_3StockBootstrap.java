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

import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.repositories.ItemUnitRepository;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.repositories.UnitRepository;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.services.InventoryService;
import com.bartolay.inventory.stock.entity.StockOpening;
import com.bartolay.inventory.stock.entity.StockOpeningItem;
import com.bartolay.inventory.stock.repositories.StockOpeningItemRepository;
import com.bartolay.inventory.stock.repositories.StockOpeningRepository;

@Component
@Transactional
public class _3StockBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private StockOpeningRepository stockOpeningRepository;

	@Autowired
	private StockOpeningItemRepository stockOpeningItemRepository;


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private InventoryService inventoryService;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemUnitRepository itemUnitRepository;

	@Autowired
	private UnitRepository unitRepository;

	private User admin;

	@Override
	public int getOrder() {
		return 3;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		List<StockOpeningItem> soItems = new ArrayList<>();

		Item item1 = itemRepository.findById(1).get();

		StockOpeningItem soItem = new StockOpeningItem();
		soItem.setItem(item1);
		soItem.setUnit(unitRepository.findById(1).get());
		soItem.setQuantity(new BigDecimal("20.1234"));
		soItem.setUnitCost(new BigDecimal("1.50"));

		admin = userRepository.findByUsername("admin");

		StockOpening stockOpening = new StockOpening();
		stockOpening.setDocumentNumber("XVMill221");
		stockOpening.setYear("2018");
		stockOpening.setLocation(locationRepository.findById(1).get());
		stockOpening.setTransactionDate(new Date());
		stockOpening.setCreatedBy(admin);
		stockOpening.setItems(soItems);

		soItem.setStockOpening(stockOpening);
		soItems.add(soItem);

		System.err.println("stockOpening");
		System.err.println(stockOpening);

		inventoryService.createStockOpening(stockOpening);

	}



	//	StockOpening opening2 = new StockOpening();
	//	opening2.setDocumentNumber("35-22-GA2");
	//	opening2.setYear("2016");
	//	opening2.setLocation(locationRepository.findById(1).get());
	//	opening2.setTransactionDate(new Date());
	//	opening2.setCreatedBy(admin);
	//	
	//	stockOpeningRepository.save(opening2);
	//	
	//	StockOpening opening3 = new StockOpening();
	//	opening3.setDocumentNumber("377MLXzz");
	//	opening3.setYear("2017");
	//	opening3.setLocation(locationRepository.findById(1).get());
	//	opening3.setTransactionDate(new Date());
	//	opening3.setCreatedBy(admin);
	//	
	//	stockOpeningRepository.save(opening3);
	//	
	//	StockOpening opening4 = new StockOpening();
	//	opening4.setDocumentNumber("DOC2123");
	//	opening4.setYear("2018");
	//	opening4.setLocation(locationRepository.findById(1).get());
	//	opening4.setTransactionDate(new Date());
	//	opening4.setCreatedBy(admin);
	//	
	//	stockOpeningRepository.save(opening4);
}
