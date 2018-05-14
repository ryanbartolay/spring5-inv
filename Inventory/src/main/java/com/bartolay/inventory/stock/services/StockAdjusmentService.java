package com.bartolay.inventory.stock.services;

import java.util.Map;

import org.json.JSONObject;

public interface StockAdjusmentService {

	JSONObject retrieveDatatableList(Map<String, String> requestMap);
	
}
