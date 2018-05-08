package com.bartolay.inventory.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.entity.stock.OpeningStock;
import com.bartolay.inventory.form.OpeningStockForm;
import com.bartolay.inventory.services.OpeningStockService;

@Service
@Transactional
public class OpeningStockServiceImpl implements OpeningStockService {

	@Override
	public List<OpeningStock> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
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
