package com.bartolay.inventory.services;

import java.util.Map;

import com.bartolay.inventory.form.BrandForm;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface BrandService<E> {
	public E create(BrandForm brandForm);

	public ObjectNode retrieveList(Map<String, String> requestMap);
}
