package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.form.BrandForm;

public interface BrandService {
	public Brand create(BrandForm brandForm);
	public Brand update(BrandForm brandForm);
	
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public Brand delete(int id);
	
	public List<Brand> findAll();
}
