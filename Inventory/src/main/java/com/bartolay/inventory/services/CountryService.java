package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Category;
import com.bartolay.inventory.entity.Country;
import com.bartolay.inventory.form.CategoryForm;
import com.bartolay.inventory.form.CountryForm;

public interface CountryService {

	public List<Country> findAll();
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public Country create(@Valid CountryForm countryForm);
	public Country update(@Valid CountryForm countryForm);
	public Country delete(Long id);
	
}
