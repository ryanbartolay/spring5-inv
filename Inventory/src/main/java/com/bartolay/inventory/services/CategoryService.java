package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Category;
import com.bartolay.inventory.form.CategoryForm;

public interface CategoryService {

	public List<Category> findAll();
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public Category create(@Valid CategoryForm categoryForm);
	public Category update(@Valid CategoryForm categoryForm);
	public Category delete(Long id);
	
}
