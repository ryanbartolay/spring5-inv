package com.bartolay.inventory.stock.services.impl;

import java.text.ParseException;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.exceptions.StockAdjustmentException;
import com.bartolay.inventory.form.StockAdjustmentForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.services.InventoryCoreService;
import com.bartolay.inventory.stock.entity.StockAdjustment;
import com.bartolay.inventory.stock.entity.StockAdjustmentReason;
import com.bartolay.inventory.stock.repositories.StockAdjustmentReasonRepository;
import com.bartolay.inventory.stock.services.StockAdjustmentService;
import com.bartolay.inventory.utils.UserCredentials;

@Service
public class StockAdjusmentServiceImpl implements StockAdjustmentService {

	@Autowired
	@Qualifier("stockAdjustmentDatatableRepository")
	private DatatableRepository stockAdjustmentDatatableRepository;
	@Autowired
	private StockAdjustmentReasonRepository stockAdjustmentReasonRepository;
	@Autowired 
	private UserCredentials userCredentials;
	
	@Autowired
	private InventoryCoreService inventoryCoreService;
	
	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = stockAdjustmentDatatableRepository.findAllData(parameter);
		long recordsTotal = stockAdjustmentDatatableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}
	
	public void createAdjustmentReason(StockAdjustmentReason reason) throws StockAdjustmentException {
		
		if(reason.getCode() == null) {
			throw new StockAdjustmentException("Reason Code is required.");
		}
		
		if(reason.getDescription() == null) {
			throw new StockAdjustmentException("Dscription is required.");
		}
		
		reason.setDescription(reason.getDescription().trim());
		reason.setCode(reason.getCode().toUpperCase().trim());
		reason.setCreatedBy(userCredentials.getLoggedInUser());
		
		stockAdjustmentReasonRepository.save(reason);
	}

	@Override
	public StockAdjustment create(StockAdjustmentForm stockAdjustmentForm)
			throws ParseException, StockAdjustmentException {
		// TODO Auto-generated method stub
		
		StockAdjustment sa = new StockAdjustment();
		sa.setStockAdjustmentType(stockAdjustmentForm.getAdjustmentType().name());
		sa.setDocumentNumber(stockAdjustmentForm.getDocument_number());
		sa.setStockAdjustmentReason(stockAdjustmentForm.getReason());
		sa.setLocation(stockAdjustmentForm.getLocation());
		sa.setTransactionDate(stockAdjustmentForm.getTransactionDate());
		sa.setYear(stockAdjustmentForm.getYear());
		sa.setDescription(stockAdjustmentForm.getDescription());
		sa.setStockAdjustmentItems(stockAdjustmentForm.getItems());
		inventoryCoreService.createStockAdjustment(sa);
		return sa;
	}

}
