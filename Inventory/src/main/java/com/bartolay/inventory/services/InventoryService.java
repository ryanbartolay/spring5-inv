package com.bartolay.inventory.services;

import java.util.Map;

import org.json.JSONObject;

public interface InventoryService {

	JSONObject retrieveDatatableListByLocationId(Map<String, String> requestMap, Integer location_id);
	JSONObject retrieveDatatableListByLocationIdWithLimi(Map<String, String> requestMap, Integer location_id, Integer limit);
}
