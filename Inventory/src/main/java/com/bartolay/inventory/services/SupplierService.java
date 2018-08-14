package com.bartolay.inventory.services;

import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Supplier;
import com.bartolay.inventory.exceptions.StockReceivedException;
import com.bartolay.inventory.exceptions.SupplierException;
import com.bartolay.inventory.form.SupplierForm;

public interface SupplierService {
	public JSONObject create(SupplierForm supplierForm) throws SupplierException;

	public JSONObject delete(Integer id) throws StockReceivedException;

	public Iterable<Supplier> findAll();

	public JSONObject findAll(Map<String, String> requestMap);

	public Supplier findById(Integer id);
}
