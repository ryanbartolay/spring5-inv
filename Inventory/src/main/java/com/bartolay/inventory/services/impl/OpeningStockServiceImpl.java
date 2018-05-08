package com.bartolay.inventory.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.stock.OpeningStock;
import com.bartolay.inventory.form.OpeningStockForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.repositories.impl.CategoryDataTableRepositoryImpl;
import com.bartolay.inventory.services.OpeningStockService;

@Service
@Transactional
public class OpeningStockServiceImpl implements OpeningStockService {

	@Autowired
//	private DatatableRepository categoryDataTableRepository;
	
	@Override
	public List<OpeningStock> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
//		DatatableParameter parameter = new DatatableParameter(requestMap);
//		JSONArray array = categoryDataTableRepository.findAllData(parameter);
//		long recordsTotal = categoryDataTableRepository.findAllCount(parameter);
//		
//		JSONObject object = new JSONObject();
//		object.put("data", array);
//		object.put("recordsTotal", recordsTotal);
//		object.put("recordsFiltered", recordsTotal);
//		object.put("draw", parameter.getDraw());
		
		return null;
	}

	@Override
	public OpeningStock create(@Valid OpeningStockForm openingStockForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpeningStock update(@Valid OpeningStockForm openingStockForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OpeningStock delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
