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

import com.bartolay.inventory.entity.Inventory;
import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.enums.StockAdjustmentType;
import com.bartolay.inventory.exceptions.StockAdjustmentException;
import com.bartolay.inventory.form.StockAdjustmentForm;
import com.bartolay.inventory.form.StockAdjustmentReasonForm;
import com.bartolay.inventory.repositories.InventoryRepository;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.stock.entity.StockAdjustment;
import com.bartolay.inventory.stock.entity.StockAdjustmentItem;
import com.bartolay.inventory.stock.repositories.StockAdjustmentItemRepository;
import com.bartolay.inventory.stock.repositories.StockAdjustmentReasonRepository;
import com.bartolay.inventory.stock.services.StockAdjustmentService;
import com.bartolay.inventory.utils.UserCredentials;

@Component
@Transactional
public class _12StockAdjustment implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered{
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private StockAdjustmentService stockAdjusmentService;
	
	@Autowired
	private StockAdjustmentReasonRepository stockAdjustmentReasonRepository;

	@Autowired
	private StockAdjustmentItemRepository stockAdjustmentItemRepository;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private StockAdjustmentService stockAdjustmentService;
	
	@Autowired
	private UserCredentials userCredentials;
	
	@Autowired
	private DateFormat dateFormat;
	
	@Override
	public int getOrder() {
		return 12;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		try {
			createStockAdjustmentReasons();
			
			Location location = locationRepository.findById(2).get();
			List<Inventory> inventories = inventoryRepository.findByLocation(location);
			
			List<StockAdjustmentItem> items = new ArrayList<>();
			
			StockAdjustmentItem item = new StockAdjustmentItem();
			item.setInventory(inventories.get(0));
			item.setQuantity(new BigDecimal("200"));
			item.setQuantityPrevious(inventories.get(0).getQuantity());
			item.setCost(new BigDecimal("0"));
			item.setCostPrevious(inventories.get(0).getUnitCost());
			item.setDescription("description");
			
			items.add(item);
			
			StockAdjustmentItem item2 = new StockAdjustmentItem();
			item2.setInventory(inventories.get(1));
			item2.setQuantity(new BigDecimal("450"));
			item2.setQuantityPrevious(inventories.get(1).getQuantity());
			item2.setCost(new BigDecimal("0"));
			item2.setCostPrevious(inventories.get(1).getUnitCost());
			item2.setDescription("description2s");
			
			items.add(item2);
			
			StockAdjustmentForm form = new StockAdjustmentForm();
			form.setDocument_number("SSS-DSSDA1234");
			form.setLocation(location);
			form.setReason(stockAdjustmentReasonRepository.findById(2).get());
			form.setAdjustmentType(StockAdjustmentType.QUANTITY);
			form.setTransactionDate(dateFormat.format(new Date()));
			form.setDescription("Loren Ipsum Dolor");
			form.setYear("2018");
			form.setItems(items);
			
			stockAdjustmentService.create(form);
			
		} catch (StockAdjustmentException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}

	private void createStockAdjustmentReasons() throws StockAdjustmentException {
		
		List<StockAdjustmentReasonForm> reasons = new ArrayList<>();
		
		StockAdjustmentReasonForm reason = new StockAdjustmentReasonForm();
		reason.setDescription("Stock on Fire");
		stockAdjusmentService.createAdjustmentReason(reason);
		
		reasons.add(reason);
		
		reason = new StockAdjustmentReasonForm();
		reason.setDescription("Stolen goods");
		stockAdjusmentService.createAdjustmentReason(reason);
		
		reasons.add(reason);
		
		reason = new StockAdjustmentReasonForm();
		reason.setDescription("Damaged goods");
		stockAdjusmentService.createAdjustmentReason(reason);
		
		reasons.add(reason);
		
		reason = new StockAdjustmentReasonForm();
		reason.setDescription("Stock Cancelled");
		stockAdjusmentService.createAdjustmentReason(reason);
		
		reasons.add(reason);
		
		reason = new StockAdjustmentReasonForm();
		reason.setDescription("Stock Taking Results");
		stockAdjusmentService.createAdjustmentReason(reason);
		
		reasons.add(reason);
		
		reason = new StockAdjustmentReasonForm();
		reason.setDescription("Inventory Revaluation");
		stockAdjusmentService.createAdjustmentReason(reason);
		
//		reasons.add(reason);
		
		
	}

}
