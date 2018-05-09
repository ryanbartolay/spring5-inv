package com.bartolay.inventory.stock.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.form.StockOpeningForm;
import com.bartolay.inventory.stock.entity.StockOpening;

public interface StockOpeningService {
	public List<StockOpening> findAll();
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public StockOpening create(StockOpeningForm openingStockForm);
	public StockOpening update(StockOpeningForm openingStockForm);
	public StockOpening delete(Long id);
}
