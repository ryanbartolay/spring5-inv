package com.bartolay.inventory.services;

import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Supplier;

public interface SupplierService {
	public JSONObject findAll(Map<String, String> requestMap);
	public Supplier findById(Integer id);
}
