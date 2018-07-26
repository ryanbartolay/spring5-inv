package com.bartolay.inventory.stock.services;

import java.text.ParseException;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.exceptions.StockReceivedException;
import com.bartolay.inventory.form.StockReceivedForm;
import com.bartolay.inventory.stock.entity.StockReceived;
import com.bartolay.inventory.stock.entity.StockReceivedExpense;

public interface StockReceivedService {
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	@Deprecated
	public JSONObject retrieveExpensesDatatableList(Map<String, String> requestMap);
	public StockReceived create(StockReceivedForm openingStockForm) throws StockReceivedException, ParseException;
}
