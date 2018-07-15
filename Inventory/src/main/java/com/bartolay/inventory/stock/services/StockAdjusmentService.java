package com.bartolay.inventory.stock.services;

import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.stock.entity.StockAdjustmentReason;

public interface StockAdjusmentService {

	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public void createAdjusmentReason(StockAdjustmentReason reason);
	
}
