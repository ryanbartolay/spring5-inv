package com.bartolay.inventory.stock.services;

import java.text.ParseException;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.exceptions.StockAdjustmentException;
import com.bartolay.inventory.exceptions.StockTransferException;
import com.bartolay.inventory.form.StockAdjustmentForm;
import com.bartolay.inventory.stock.entity.StockAdjustment;
import com.bartolay.inventory.stock.entity.StockAdjustmentReason;

public interface StockAdjustmentService {

	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public void createAdjustmentReason(StockAdjustmentReason reason) throws StockAdjustmentException;
	
	public StockAdjustment create(StockAdjustmentForm stockAdjustmentForm) throws ParseException, StockAdjustmentException;
	
}
