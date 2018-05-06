package com.bartolay.inventory.services.impl;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Category;
import com.bartolay.inventory.repositories.CategoryRepository;
import com.bartolay.inventory.services.CategoryService;
import com.bartolay.inventory.utils.ServiceUtility;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public List<Category> findAll() {
		return ServiceUtility.toList(categoryRepository.findAll());
	}

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		System.err.println(parameter);
		List<Category> categories = null;
//		if(parameter.getSortOrder().equals(SortOrder.ASC)) {
//			colors = colorRepository.findAllByOrderByColorAsc();
//		}else {
//			colors = colorRepository.findAllByOrderByColorDesc();
//		}
		long recordsTotal = categoryRepository.count();
		JSONObject object = new JSONObject();
		object.put("data", categories);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		return object;
	}
}
