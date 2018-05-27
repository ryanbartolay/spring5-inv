package com.bartolay.inventory.stock.services;

import java.text.ParseException;
import java.util.Map;

import com.bartolay.inventory.form.StockTransferForm;
import com.bartolay.inventory.stock.entity.StockTransfer;

public interface StockTransferService {

	public StockTransfer create(StockTransferForm stockTransferForm) throws ParseException;
	Object retrieveDatatableList(Map<String, String> requestMap);

}
