package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Company;

public interface CompanyService {
	public List<Company> findAll();
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public Company delete(Long id);
	public Company update(Company company);
}
