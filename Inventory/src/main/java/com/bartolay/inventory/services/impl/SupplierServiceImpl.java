package com.bartolay.inventory.services.impl;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.repositories.SupplierJdbcRepository;
import com.bartolay.inventory.services.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierJdbcRepository supplierJdbcRepository;

	@Override
	public JSONObject findAll(Map<String, String> requestMap) {
		DatatableParameter datatableParameter = new DatatableParameter(requestMap);
		
		JSONObject object = new JSONObject();
		object.put("data", supplierJdbcRepository.findAllData(datatableParameter));
		object.put("recordsTotal", supplierJdbcRepository.findAllCount(datatableParameter));

		return object;
	}
}
