package com.bartolay.inventory.services.impl;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.repositories.InventoryRepository;
import com.bartolay.inventory.repositories.impl.InventoryDatatableRepository;
import com.bartolay.inventory.services.InventoryService;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryDatatableRepository inventoryJdbcRepository;

	@Override
	public JSONObject retrieveDatatableListByLocationId(Map<String, String> requestMap, Integer location_id) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = inventoryJdbcRepository.findAllDataByLocationId(parameter, location_id);
		long recordsTotal = inventoryJdbcRepository.findAllCountByLocationId(parameter, location_id);
		
		System.err.println(array);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	public JSONObject retrieveDatatableListByLocationIdWithLimi(Map<String, String> requestMap, Integer location_id, Integer limit) {
		JSONArray array = inventoryJdbcRepository.findAllDataByLocationId(location_id, limit);
		JSONObject object = new JSONObject();
		object.put("data", array);
		return object;
	}
}
