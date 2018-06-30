package com.bartolay.inventory.sales.services;

import java.util.Map;

import org.json.JSONObject;

public interface SalesReturnService {

	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	
}
