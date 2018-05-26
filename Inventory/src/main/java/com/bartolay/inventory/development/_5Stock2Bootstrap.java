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
public class _5Stock2Bootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

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
	
	@Override
	public int getOrder() {
		return 5;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		// opening 2 ---------------------------------------------------
		List<StockOpeningItem> soItems = new ArrayList<>();


		Item item2 = itemRepository.findById(1).get();
		Item item3 = itemRepository.findById(2).get();


		StockOpeningItem soItem = new StockOpeningItem();
		soItem.setItem(item2);
		soItem.setUnit(unitRepository.findById(1).get());
		soItem.setQuantity(new BigDecimal("77.1234"));
		soItem.setUnitCost(new BigDecimal("19.560"));

		StockOpeningItem soItem2 = new StockOpeningItem();
		soItem2.setItem(item3);
		soItem2.setUnit(unitRepository.findById(1).get());
		soItem2.setQuantity(new BigDecimal("55.1234"));
		soItem2.setUnitCost(new BigDecimal("92.560"));

		User admin = userRepository.findByUsername("admin");

		StockOpening stockOpening2 = new StockOpening();
		stockOpening2.setDocumentNumber("XVMill222");
		stockOpening2.setYear("2018");
		stockOpening2.setLocation(locationRepository.findById(1).get());
		stockOpening2.setTransactionDate(new Date());
		stockOpening2.setCreatedBy(admin);
		stockOpening2.setItems(soItems);

		soItem.setStockOpening(stockOpening2);
		soItem2.setStockOpening(stockOpening2);
		soItems.add(soItem);
		soItems.add(soItem2);

		System.err.println("stockOpening22222222");
		System.err.println(stockOpening2);

//		inventoryService.createStockOpening(stockOpening2);
	}

}
