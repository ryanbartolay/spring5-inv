package com.bartolay.inventory.repositories;

import org.json.JSONArray;

import com.bartolay.inventory.datatable.model.DatatableParameter;

public interface UserDatatableRepository {
	public long findAllCount(DatatableParameter datatableParameter);
	public JSONArray findAllData(DatatableParameter datatableParameter);
}
