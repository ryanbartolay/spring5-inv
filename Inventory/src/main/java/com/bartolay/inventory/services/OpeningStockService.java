package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;

import com.bartolay.inventory.entity.stock.OpeningStock;
import com.bartolay.inventory.form.OpeningStockForm;

public interface OpeningStockService {
	public List<OpeningStock> findAll();
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public OpeningStock create(@Valid OpeningStockForm openingStockForm);
	public OpeningStock update(@Valid OpeningStockForm openingStockForm);
	public OpeningStock delete(Long id);
}
