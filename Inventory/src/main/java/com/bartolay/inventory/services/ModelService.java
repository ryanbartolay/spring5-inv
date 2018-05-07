package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Category;
import com.bartolay.inventory.entity.Model;
import com.bartolay.inventory.form.CategoryForm;
import com.bartolay.inventory.form.ModelForm;

public interface ModelService {

	public List<Model> findAll();
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public Model create(@Valid ModelForm modelForm);
	public Model update(@Valid ModelForm modelForm);
	public Model delete(Long id);
}
