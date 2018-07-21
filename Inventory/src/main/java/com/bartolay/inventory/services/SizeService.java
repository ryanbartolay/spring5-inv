package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Size;
import com.bartolay.inventory.form.SizeForm;

public interface SizeService {
	public Size create(SizeForm sizeForm);
	public Size update(SizeForm sizeForm);
	
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public Size delete(Integer id);
	
	public List<Size> findAll();
}
