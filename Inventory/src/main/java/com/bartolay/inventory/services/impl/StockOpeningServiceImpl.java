package com.bartolay.inventory.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.stock.StockOpening;
import com.bartolay.inventory.form.OpeningStockForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.services.StockOpeningService;

@Service
@Transactional
public class StockOpeningServiceImpl implements StockOpeningService {

	@Autowired
	@Qualifier("openingStockDatatableRepository")
	private DatatableRepository openingStockDatatableRepository;
	
	@Override
	public List<StockOpening> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = openingStockDatatableRepository.findAllData(parameter);
		long recordsTotal = openingStockDatatableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	public StockOpening create(@Valid OpeningStockForm openingStockForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockOpening update(@Valid OpeningStockForm openingStockForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockOpening delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
