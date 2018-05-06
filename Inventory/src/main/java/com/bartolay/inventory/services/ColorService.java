package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.entity.Color;
import com.bartolay.inventory.entity.Company;
import com.bartolay.inventory.form.ColorForm;

public interface ColorService {

	public List<Color> findAll();
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public Color create(@Valid ColorForm colorForm);
	public Color update(@Valid ColorForm colorForm);
	public Color delete(Long id);
	
}
