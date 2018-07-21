package com.bartolay.inventory.stock.services.impl;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.enums.ActivityType;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.exceptions.StockReceiveException;
import com.bartolay.inventory.form.StockReceivedForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.services.InventoryCoreService;
import com.bartolay.inventory.stock.entity.StockReceived;
import com.bartolay.inventory.stock.services.StockReceivedService;
import com.bartolay.inventory.utils.ActivityUtility;

@Service
public class StockReceivedServiceImpl implements StockReceivedService {

	@Autowired
	private InventoryCoreService inventoryCoreService;
	
	@Autowired
	private ActivityUtility activityUtility;
	
	@Autowired
	@Qualifier("stockReceivedDatatableRepository")
	private DatatableRepository stockReceivedDatatableRepository;
	
	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = stockReceivedDatatableRepository.findAllData(parameter);
		long recordsTotal = stockReceivedDatatableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	public JSONObject retrieveExpensesDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = stockReceivedDatatableRepository.findAllData(parameter);
		long recordsTotal = stockReceivedDatatableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	
	@Override
	public StockReceived create(StockReceivedForm stockReceiveForm) throws StockReceiveException {
		
		if(stockReceiveForm.getStockReceiveItems().size() <= 0) {
			throw new StockReceiveException("Required atleast 1 item.");
		}
		
		if(stockReceiveForm.getPaymentMethod() == null) {
			throw new StockReceiveException("Payment method is required");
		}
		
		if(stockReceiveForm.getPaymentMethod() != null && stockReceiveForm.getPaymentMethod().equals(PaymentMethod.CREDITCARD) 
				&& stockReceiveForm.getCreditCardDetails() == null) {
			throw new StockReceiveException("Credit Card Details must be specified");
		}
		
		if(stockReceiveForm.getSupplier() == null) {
			throw new StockReceiveException("Supplier is required");
		}
		
		StockReceived stockReceived = new StockReceived();
		stockReceived.setLocation(stockReceiveForm.getLocation());
		stockReceived.setDocumentNumber(stockReceiveForm.getDocument_number());
		stockReceived.setStockReceiveItems(stockReceiveForm.getStockReceiveItems());
		stockReceived.setPaymentMethod(stockReceiveForm.getPaymentMethod());
		stockReceived.setStockReceiveExpenses(stockReceiveForm.getExpenses());
		stockReceived.setDiscountValue(stockReceiveForm.getDiscountValue());
		stockReceived.setYear(stockReceiveForm.getYear());
		stockReceived.setTransactionDate(stockReceiveForm.getTransactionDate());
		stockReceived.setSupplier(stockReceiveForm.getSupplier());
		stockReceived.setCreditCardDetails(stockReceiveForm.getCreditCardDetails());
		stockReceived.setDescription(stockReceiveForm.getDescription());
		
		stockReceived = inventoryCoreService.createStockReceive(stockReceived);
		activityUtility.log(ActivityType.STOCK_RECEIVED, stockReceived);
		
		return stockReceived;
	}

}
