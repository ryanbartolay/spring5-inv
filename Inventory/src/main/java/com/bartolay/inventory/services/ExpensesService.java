package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Country;
import com.bartolay.inventory.entity.Expense;
import com.bartolay.inventory.form.CountryForm;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ExpensesService {

	public List<Expense> findAll();
	public ObjectNode retrieveDatatableList();
	public Country create(@Valid CountryForm countryForm);
	public Country update(@Valid CountryForm countryForm);
	public Country delete(int id);
	
}
