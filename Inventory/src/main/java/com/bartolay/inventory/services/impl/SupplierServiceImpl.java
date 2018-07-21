package com.bartolay.inventory.services.impl;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Supplier;
import com.bartolay.inventory.repositories.SupplierJdbcRepository;
import com.bartolay.inventory.repositories.SupplierRepository;
import com.bartolay.inventory.services.SupplierService;
import com.bartolay.inventory.utils.ServiceUtility;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierJdbcRepository supplierJdbcRepository;

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Override
	public JSONObject findAll(Map<String, String> requestMap) {
		DatatableParameter datatableParameter = new DatatableParameter(requestMap);
		
		JSONObject object = new JSONObject();
		object.put("data", supplierJdbcRepository.findAllData(datatableParameter));
		object.put("recordsTotal", supplierJdbcRepository.findAllCount(datatableParameter));

		return object;
	}

	@Override
	public Supplier findById(Integer id) {
		return supplierRepository.findById(id).get();
	}

	@Override
	public List<Supplier> findAll() {
		return ServiceUtility.toList(supplierRepository.findAll());
	}
}
