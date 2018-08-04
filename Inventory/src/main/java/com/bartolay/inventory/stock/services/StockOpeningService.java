package com.bartolay.inventory.stock.services;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.exceptions.StockOpeningException;
import com.bartolay.inventory.form.StockOpeningForm;
import com.bartolay.inventory.stock.entity.StockOpening;

public interface StockOpeningService {
	public List<StockOpening> findAll();
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public StockOpening create(StockOpeningForm openingStockForm) throws ParseException, StockOpeningException;
	public StockOpening update(StockOpeningForm openingStockForm);
	public StockOpening delete(Long id);
}
