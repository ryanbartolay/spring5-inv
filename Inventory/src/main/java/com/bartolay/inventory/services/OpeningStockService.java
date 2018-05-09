package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;

import com.bartolay.inventory.entity.stock.StockOpening;
import com.bartolay.inventory.form.OpeningStockForm;

public interface OpeningStockService {
	public List<StockOpening> findAll();
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public StockOpening create(@Valid OpeningStockForm openingStockForm);
	public StockOpening update(@Valid OpeningStockForm openingStockForm);
	public StockOpening delete(Long id);
}
