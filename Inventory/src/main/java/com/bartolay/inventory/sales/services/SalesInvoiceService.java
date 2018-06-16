package com.bartolay.inventory.sales.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.exceptions.SalesInvoiceException;
import com.bartolay.inventory.form.SalesInvoiceForm;
import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.entity.SalesInvoiceItem;

public interface SalesInvoiceService {
	public SalesInvoice create(SalesInvoiceForm salesInvoiceForm) throws SalesInvoiceException;

	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public List<SalesInvoice> findAll();
	public SalesInvoice cancel(String systemNumber);

	public List<SalesInvoiceItem> retrieveItemsList(String systemNumber);
}
