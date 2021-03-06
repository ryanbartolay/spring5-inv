package com.bartolay.inventory.sales.services;

import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.exceptions.SalesReturnException;
import com.bartolay.inventory.form.SalesReturnForm;
import com.bartolay.inventory.sales.entity.SalesReturn;

public interface SalesReturnService {

	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	SalesReturn create(SalesReturnForm returnForm) throws SalesReturnException;
	
}
