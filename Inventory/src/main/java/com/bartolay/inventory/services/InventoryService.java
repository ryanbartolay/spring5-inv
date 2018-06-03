package com.bartolay.inventory.services;

import java.util.Map;

import org.json.JSONObject;

public interface InventoryService {

	public JSONObject retrieveDatatableListByLocationId(Map<String, String> requestMap, Integer location_id);
}
