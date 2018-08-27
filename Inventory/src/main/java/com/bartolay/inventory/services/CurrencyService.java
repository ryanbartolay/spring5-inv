package com.bartolay.inventory.services;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Currency;
import com.bartolay.inventory.exceptions.CurrencyException;
import com.bartolay.inventory.form.CurrencyForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface CurrencyService {

	public Iterable<Currency> retrieveList();
	public ObjectNode retrieveDatatableList(Map<String, String> requestMap) throws JsonProcessingException, UnsupportedEncodingException;
	public JSONObject create(@Valid CurrencyForm expenseForm) throws CurrencyException;
	public JSONObject update(@Valid CurrencyForm expenseForm) throws CurrencyException;
	public JSONObject delete(Integer id) throws CurrencyException;
	public Currency findById(Integer currencyId) throws JsonProcessingException, UnsupportedEncodingException;

}
