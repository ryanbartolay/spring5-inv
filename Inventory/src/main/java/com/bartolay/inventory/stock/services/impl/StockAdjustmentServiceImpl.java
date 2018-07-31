package com.bartolay.inventory.stock.services.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.exceptions.StockAdjustmentException;
import com.bartolay.inventory.form.StockAdjustmentForm;
import com.bartolay.inventory.form.StockAdjustmentReasonForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.services.InventoryCoreService;
import com.bartolay.inventory.stock.entity.StockAdjustment;
import com.bartolay.inventory.stock.entity.StockAdjustmentReason;
import com.bartolay.inventory.stock.repositories.StockAdjustmentReasonRepository;
import com.bartolay.inventory.stock.repositories.StockAdjustmentRepository;
import com.bartolay.inventory.stock.services.StockAdjustmentService;
import com.bartolay.inventory.utils.UserCredentials;

@Service
public class StockAdjustmentServiceImpl implements StockAdjustmentService {

	@Autowired
	private DateFormat dateFormat;
	@Autowired
	@Qualifier("stockAdjustmentDatatableRepository")
	private DatatableRepository stockAdjustmentDatatableRepository;
	@Autowired
	private StockAdjustmentRepository stockAdjustmentRepository;
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
	
	@Override
	public Iterable<StockAdjustmentReason> retrieveReasonList() {
		return stockAdjustmentReasonRepository.apiFindAll();
	}
	
	public JSONObject createAdjustmentReason(StockAdjustmentReasonForm stockAdjustmentReasonForm) throws StockAdjustmentException {
		
		StockAdjustmentReason reason = new StockAdjustmentReason();
		reason.setCode(stockAdjustmentReasonForm.getDescription().trim().replace(" ", "_").toUpperCase());
		reason.setDescription(stockAdjustmentReasonForm.getDescription());
		reason.setCreatedBy(userCredentials.getLoggedInUser());
		
		reason = stockAdjustmentReasonRepository.save(reason);
		
		JSONObject obj = new JSONObject();
		obj.put("status", "OK");
		obj.put("id", reason.getId());
		obj.put("code", reason.getCode());
		obj.put("description", reason.getDescription());
		
		return obj;
	}

	@Override
	public StockAdjustment create(StockAdjustmentForm stockAdjustmentForm)
			throws ParseException, StockAdjustmentException {
		
		if(stockAdjustmentForm.getItems().size() <= 0) {
			throw new StockAdjustmentException("Atleast 1 item is required.");
		}
		
		Date date = dateFormat.parse(stockAdjustmentForm.getTransactionDate());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		stockAdjustmentForm.getItems().forEach(item -> { 
			if(item.getCostPrevious() == null) {
				item.setCostPrevious(new BigDecimal("0"));
			}
			
			if(item.getQuantityPrevious() == null) {
				item.setQuantityPrevious(new BigDecimal("0"));
			}
		});
		
		StockAdjustment sa = new StockAdjustment();
		sa.setStockAdjustmentType(stockAdjustmentForm.getAdjustmentType().name());
		sa.setDocumentNumber(stockAdjustmentForm.getDocument_number());
		sa.setStockAdjustmentReason(stockAdjustmentForm.getReason());
		sa.setLocation(stockAdjustmentForm.getLocation());
		sa.setTransactionDate(cal.getTime());
		sa.setYear(stockAdjustmentForm.getYear());
		sa.setDescription(stockAdjustmentForm.getDescription());
		sa.setStockAdjustmentItems(stockAdjustmentForm.getItems());

		inventoryCoreService.createStockAdjustment(sa);
		return sa;
	}

	@Override
	public JSONObject deleteAdjustmentReason(StockAdjustmentReason reason) throws StockAdjustmentException {
		
		List<StockAdjustment> stockAdjustments = stockAdjustmentRepository.findAllByStockAdjustmentReason(reason);
		
		if(stockAdjustments.size() > 0) {
			String sql = "Unable to delete this reason. You must delete first these Stock Adjustment.";
			
			List<String> errors = new ArrayList<>();
			for(StockAdjustment adjustment : stockAdjustments) {
				errors.add(adjustment.getSystemNumber() + " " + adjustment.getDocumentNumber() + "\n");
			}
			
			throw new StockAdjustmentException(sql,errors);
		}
		
		stockAdjustmentReasonRepository.delete(reason);
		JSONObject obj = new JSONObject();
		obj.put("status", "OK");
		obj.put("localizedMessage", "Reason deleted");
		return obj;
	}
}
