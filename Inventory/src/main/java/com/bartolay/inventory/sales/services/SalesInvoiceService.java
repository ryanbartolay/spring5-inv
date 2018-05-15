package com.bartolay.inventory.sales.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.form.SalesInvoiceForm;
import com.bartolay.inventory.sales.entity.SalesInvoice;

public interface SalesInvoiceService {
	public SalesInvoice create(SalesInvoiceForm salesInvoiceForm);
	public SalesInvoice update(SalesInvoiceForm salesInvoiceForm);
	
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public SalesInvoice delete(Long id);
	
	public List<SalesInvoice> findAll();
	public SalesInvoice cancel(String systemNumber);
}
