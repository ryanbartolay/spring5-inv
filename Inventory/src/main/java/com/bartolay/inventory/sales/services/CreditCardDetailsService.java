package com.bartolay.inventory.sales.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.form.CreditCardDetailsForm;
import com.bartolay.inventory.sales.entity.CreditCardDetails;

public interface CreditCardDetailsService {
	public CreditCardDetails create(CreditCardDetailsForm ccForm);
	public CreditCardDetails update(CreditCardDetailsForm ccForm);
	
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public CreditCardDetails delete(Long id);
	
	public List<CreditCardDetails> findAll();
}
