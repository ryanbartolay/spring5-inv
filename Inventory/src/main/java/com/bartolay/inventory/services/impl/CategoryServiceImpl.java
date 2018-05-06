package com.bartolay.inventory.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Category;
import com.bartolay.inventory.form.CategoryForm;
import com.bartolay.inventory.repositories.CategoryRepository;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.services.CategoryService;
import com.bartolay.inventory.utils.ServiceUtility;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	@Qualifier("categoryDataTableRepository")
	private DatatableRepository categoryDataTableRepository;
	
	@Override
	public List<Category> findAll() {
		return ServiceUtility.toList(categoryRepository.findAll());
	}

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = categoryDataTableRepository.findAllData(parameter);
		long recordsTotal = categoryDataTableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	public Category create(@Valid CategoryForm categoryForm) {
		Category category = new Category();
		category.setType(categoryForm.getType());
		category.setDescription(categoryForm.getDescription());
		return categoryRepository.save(category);
	}

	@Override
	public Category update(@Valid CategoryForm categoryForm) {
		Category category = categoryRepository.findById(categoryForm.getId()).get();
		if(category != null) {
			category.setType(categoryForm.getType());
			category.setDescription(categoryForm.getDescription());
			category = categoryRepository.save(category);
		}
		return category;
	}

	@Override
	public Category delete(Long id) {
		Optional<Category> caOptional = categoryRepository.findById(id);
		categoryRepository.deleteById(id);
		return caOptional.get();
	}
}
