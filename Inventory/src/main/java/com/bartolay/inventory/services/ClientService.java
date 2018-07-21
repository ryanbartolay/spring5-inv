package com.bartolay.inventory.services;

import java.util.Map;

import org.json.JSONObject;

public interface ClientService {

	JSONObject retrieveDatatableList(Map<String, String> requestMap);
	
}
