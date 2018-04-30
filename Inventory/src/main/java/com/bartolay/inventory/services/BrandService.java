package com.bartolay.inventory.services;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bartolay.inventory.datatable.BrandDatatable;
import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.form.BrandForm;
import com.bartolay.inventory.pagination.DataTableResults;

public interface BrandService {
	public Brand create(BrandForm brandForm);

	public DataTableResults<BrandDatatable> retrieveList(HttpServletRequest request);
}
