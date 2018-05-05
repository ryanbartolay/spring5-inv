package com.bartolay.inventory.repositories;

import org.json.JSONArray;

import com.bartolay.inventory.datatable.model.DatatableParameter;

public interface CompanyDatatableRepository {
	public long findAllCount(DatatableParameter datatableParameter);
	public JSONArray findAllData(DatatableParameter datatableParameter);
}
