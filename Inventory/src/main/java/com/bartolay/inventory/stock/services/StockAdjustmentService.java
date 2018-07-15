package com.bartolay.inventory.stock.services;

import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.exceptions.StockAdjustmentException;
import com.bartolay.inventory.stock.entity.StockAdjustmentReason;

public interface StockAdjustmentService {

	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public void createAdjustmentReason(StockAdjustmentReason reason) throws StockAdjustmentException;
	
}
