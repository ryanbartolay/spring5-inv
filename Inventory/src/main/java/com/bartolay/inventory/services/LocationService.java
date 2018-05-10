package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.form.LocationForm;

public interface LocationService {
	public Location create(LocationForm locationForm);
	public Location update(LocationForm locationForm);
	
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public Location delete(Integer id);
	
	public List<Location> findAll();
	public Location findOne(Integer id);
}
