package com.bartolay.inventory.services;

import java.util.Map;

import com.bartolay.inventory.form.BrandForm;
import com.bartolay.inventory.pagination.DataTableResults;

public interface BrandService<E> {
	public E create(BrandForm brandForm);

	public DataTableResults<E> retrieveList(Map<String, String> requestMap);
}
