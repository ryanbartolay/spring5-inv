package com.bartolay.inventory.stock.services;

import java.text.ParseException;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.exceptions.StockReceiveException;
import com.bartolay.inventory.form.StockReceivedForm;
import com.bartolay.inventory.stock.entity.StockReceived;

public interface StockReceivedService {
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public JSONObject retrieveExpensesDatatableList(Map<String, String> requestMap);
	public StockReceived create(StockReceivedForm openingStockForm) throws StockReceiveException, ParseException;
}
