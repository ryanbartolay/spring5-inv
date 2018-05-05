package com.bartolay.inventory.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Company;
import com.bartolay.inventory.repositories.CompanyDatatableRepository;
import com.bartolay.inventory.repositories.CompanyRepository;
import com.bartolay.inventory.services.CompanyService;
import com.bartolay.inventory.utils.ServiceUtility;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CompanyDatatableRepository companyDatatableRepository;
	
	@Override
	public List<Company> findAll() {
		return ServiceUtility.toList(companyRepository.findAll());
	}

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = companyDatatableRepository.findAllData(parameter);
		long recordsTotal = companyDatatableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}
	
}
