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

import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.repositories.UnitRepository;
import com.bartolay.inventory.services.InventoryService;
import com.bartolay.inventory.stock.entity.StockTransfer;
import com.bartolay.inventory.stock.entity.StockTransferItem;

@Component
@Transactional
public class _8StockTransferBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private UnitRepository unitRepository;
	
	@Override
	public int getOrder() {
		return 8;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		List<StockTransferItem> items = new ArrayList<>();
		
		StockTransferItem stockTransferItem1 = new StockTransferItem();
		stockTransferItem1.setItem(itemRepository.findById(1).get());
		stockTransferItem1.setUnit(unitRepository.findById(2).get());
		stockTransferItem1.setQuantity(new BigDecimal("4.5"));
		
		items.add(stockTransferItem1);
		
		StockTransfer stockTransfer = new StockTransfer();
		stockTransfer.setDocumentNumber("stransfer-101");
		stockTransfer.setFromLocation(locationRepository.findById(1).get());
		stockTransfer.setToLocation(locationRepository.findById(2).get());
		stockTransfer.setTransactionDate(new Date());
		stockTransfer.setYear("2018");
		stockTransfer.setStockTransferItems(items);
		
		inventoryService.createStockTransfer(stockTransfer);
	}

}
