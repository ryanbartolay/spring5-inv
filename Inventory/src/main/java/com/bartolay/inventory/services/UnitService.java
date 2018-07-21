package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Unit;
import com.bartolay.inventory.form.UnitForm;

public interface UnitService {
	public Unit create(UnitForm unitForm);
	public Unit update(UnitForm unitForm);
	
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public Unit delete(Integer id);
	
	public List<Unit> findAll();
}
