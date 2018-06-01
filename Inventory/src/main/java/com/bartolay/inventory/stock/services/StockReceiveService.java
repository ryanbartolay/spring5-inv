package com.bartolay.inventory.stock.services;

import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.exceptions.StockReceiveException;
import com.bartolay.inventory.form.StockReceiveForm;
import com.bartolay.inventory.stock.entity.StockReceive;

public interface StockReceiveService {
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public StockReceive create(StockReceiveForm openingStockForm) throws StockReceiveException;
}
