package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Company;
import com.bartolay.inventory.form.CompanyForm;

public interface CompanyService {
	public List<Company> findAll();
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public Company create(CompanyForm companyForm);
	public Company update(CompanyForm companyForm);
	public Company delete(Long id);
}
