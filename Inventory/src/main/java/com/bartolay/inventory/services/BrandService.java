package com.bartolay.inventory.services;

import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.form.BrandForm;

public interface BrandService<E> {
	public E create(BrandForm brandForm);

	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
}
