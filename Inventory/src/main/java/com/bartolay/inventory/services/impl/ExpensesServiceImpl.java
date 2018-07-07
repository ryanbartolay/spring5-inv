package com.bartolay.inventory.services.impl;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Country;
import com.bartolay.inventory.entity.Expense;
import com.bartolay.inventory.form.CountryForm;
import com.bartolay.inventory.repositories.ExpenseRepository;
import com.bartolay.inventory.services.ExpensesService;
import com.bartolay.inventory.utils.ServiceUtility;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class ExpensesServiceImpl implements ExpensesService {

	@Autowired
	private ExpenseRepository expenseRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public List<Expense> findAll() {
		
		return null;
	}

	@Override
	public ObjectNode retrieveDatatableList() {
		List<Expense> array = ServiceUtility.toList(expenseRepository.findAll());
		ObjectNode object = objectMapper.createObjectNode();
		object.set("data", objectMapper.convertValue(array, JsonNode.class));
		object.put("recordsTotal", array.size());
		object.put("recordsFiltered", array.size());
		object.put("draw", 0);
		
		return object;
	}

	@Override
	public Country create(@Valid CountryForm countryForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country update(@Valid CountryForm countryForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
