package com.bartolay.inventory.services.impl;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	@Qualifier("clientDataTableRepository")
	private DatatableRepository clientDataTableRepository;

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = clientDataTableRepository.findAllData(parameter);
		long recordsTotal = clientDataTableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}
	
}
