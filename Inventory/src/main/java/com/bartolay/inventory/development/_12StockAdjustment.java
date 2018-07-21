package com.bartolay.inventory.development;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.enums.StockAdjustmentType;
import com.bartolay.inventory.exceptions.StockAdjustmentException;
import com.bartolay.inventory.form.StockAdjustmentReasonForm;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.stock.entity.StockAdjustment;
import com.bartolay.inventory.stock.repositories.StockAdjustmentReasonRepository;
import com.bartolay.inventory.stock.repositories.StockAdjustmentRepository;
import com.bartolay.inventory.stock.services.StockAdjustmentService;
import com.bartolay.inventory.utils.UserCredentials;

@Component
@Transactional
public class _12StockAdjustment implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered{

	@Autowired
	private StockAdjustmentRepository stockAdjustmentRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private StockAdjustmentService stockAdjusmentService;
	
	@Autowired
	private StockAdjustmentReasonRepository stockAdjustmentReasonRepository;
	
	@Autowired
	private UserCredentials userCredentials;
	
	@Override
	public int getOrder() {
		return 12;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		try {
			createStockAdjustmentReasons();
			
			StockAdjustment entity = new StockAdjustment();
			entity.setDocumentNumber("SSS-DSSDA1234");
			entity.setLocation(locationRepository.findById(2).get());
			entity.setStockAdjustmentReason(stockAdjustmentReasonRepository.findById(2).get());
			entity.setStockAdjustmentType(StockAdjustmentType.QUANTITY.name());
			entity.setTransactionDate(new Date());
			entity.setYear("2018");
			entity.setCreatedBy(userCredentials.getLoggedInUser());
			
			stockAdjustmentRepository.save(entity);
			
		} catch (StockAdjustmentException e) {
			// TODO Auto-generated catch block
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
