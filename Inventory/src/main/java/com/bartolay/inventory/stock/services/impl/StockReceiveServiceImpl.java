package com.bartolay.inventory.stock.services.impl;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.exceptions.StockReceiveException;
import com.bartolay.inventory.form.StockReceiveForm;
import com.bartolay.inventory.services.InventoryCoreService;
import com.bartolay.inventory.stock.entity.StockReceive;
import com.bartolay.inventory.stock.services.StockReceiveService;

@Service
public class StockReceiveServiceImpl implements StockReceiveService {

	@Autowired
	private InventoryCoreService inventoryCoreService;
	
	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockReceive create(StockReceiveForm stockReceiveForm) throws StockReceiveException {
		
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
		
		StockReceive stockReceive = new StockReceive();
		stockReceive.setLocation(stockReceiveForm.getLocation());
		stockReceive.setStockReceiveItems(stockReceiveForm.getStockReceiveItems());
		stockReceive.setPaymentMethod(stockReceiveForm.getPaymentMethod());
		stockReceive.setExpenses(stockReceiveForm.getExpenses());
		stockReceive.setDiscountValue(stockReceiveForm.getDiscountValue());
		stockReceive.setYear(stockReceiveForm.getYear());
		stockReceive.setTransactionDate(stockReceiveForm.getTransactionDate());
		stockReceive.setSupplier(stockReceiveForm.getSupplier());
		
		inventoryCoreService.createStockReceive(stockReceive);
		
		return stockReceive;
	}

}
