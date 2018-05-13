package com.bartolay.inventory.stock.services.impl;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.stock.services.StockTransferService;

@Service
public class StockTransferServiceImpl implements StockTransferService {

	@Autowired
	@Qualifier("stockTransferDatatableRepository")
	private DatatableRepository stockTransferDatatableRepository;
	
	@Override
	public Object retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = stockTransferDatatableRepository.findAllData(parameter);
		long recordsTotal = stockTransferDatatableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

}
