package com.bartolay.inventory.development;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.stock.entity.StockAdjustment;
import com.bartolay.inventory.stock.entity.StockAdjustmentReason;
import com.bartolay.inventory.stock.repositories.StockAdjustmentReasonRepository;
import com.bartolay.inventory.stock.repositories.StockAdjustmentRepository;

@Component
@Transactional
public class _12StockAdjustment implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered{

	@Autowired
	private StockAdjustmentRepository stockAdjustmentRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private StockAdjustmentReasonRepository stockAdjustmentReasonRepository;
	
	@Override
	public int getOrder() {
		return 12;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		createStockAdjustmentReasons();
		
		StockAdjustment entity = new StockAdjustment();
		entity.setDocumentNumber("SSS-DSSDA1234");
		entity.setLocation(locationRepository.findById(2).get());
		entity.setStockAdjustmentReason(stockAdjustmentReasonRepository.findById(2).get());
		
		stockAdjustmentRepository.save(entity);
		
	}

	private void createStockAdjustmentReasons() {
		
		List<StockAdjustmentReason> reasons = new ArrayList<>();
		
		StockAdjustmentReason reason = new StockAdjustmentReason();
		reason.setCode("STOCK_ON_FIRE");
		reason.setDescription("Stock on Fire");
		
		reasons.add(reason);
		
		reason = new StockAdjustmentReason();
		reason.setCode("STOLEN_GOODS");
		reason.setDescription("Stolen goods");
		
		reasons.add(reason);
		
		reason = new StockAdjustmentReason();
		reason.setCode("DAMAGED_GOODS");
		reason.setDescription("Damaged goods");
		
		reasons.add(reason);
		
		reason = new StockAdjustmentReason();
		reason.setCode("STOCK_CANCELED");
		reason.setDescription("Stock Cancelled");
		
		reasons.add(reason);
		
		reason = new StockAdjustmentReason();
		reason.setCode("STOCKTAKING_RESULTS");
		reason.setDescription("Stock Taking Results");
		
		reasons.add(reason);
		
		reason = new StockAdjustmentReason();
		reason.setCode("INVENTORY_REVALUATION");
		reason.setDescription("Inventory Revaluation");
		
		reasons.add(reason);
		
		stockAdjustmentReasonRepository.saveAll(reasons);
		
	}

}
