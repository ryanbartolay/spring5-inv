package com.bartolay.inventory.stock.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.form.OpeningStockForm;
import com.bartolay.inventory.stock.entity.StockOpening;

public interface StockOpeningService {
	public List<StockOpening> findAll();
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public StockOpening create(OpeningStockForm openingStockForm);
	public StockOpening update(OpeningStockForm openingStockForm);
	public StockOpening delete(Long id);
}
