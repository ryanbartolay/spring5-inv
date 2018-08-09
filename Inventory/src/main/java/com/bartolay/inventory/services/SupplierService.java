package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Supplier;
import com.bartolay.inventory.exceptions.SupplierException;
import com.bartolay.inventory.form.SupplierForm;

public interface SupplierService {
	public JSONObject findAll(Map<String, String> requestMap);
	public Supplier findById(Integer id);
	public Iterable<Supplier> findAll();
	public JSONObject create(SupplierForm supplierForm) throws SupplierException;
}
