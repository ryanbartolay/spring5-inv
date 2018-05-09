package com.bartolay.inventory.stock.services.impl;

import java.util.Calendar;
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
import com.bartolay.inventory.form.OpeningStockForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.stock.entity.StockOpening;
import com.bartolay.inventory.stock.repositories.StockOpeningRepository;
import com.bartolay.inventory.stock.services.StockOpeningService;
import com.bartolay.inventory.stock.utils.StockUtility;
import com.bartolay.inventory.utils.UserCredentials;

@Service
@Transactional
public class StockOpeningServiceImpl implements StockOpeningService {

	@Autowired
	@Qualifier("openingStockDatatableRepository")
	private DatatableRepository openingStockDatatableRepository;

	@Autowired
	private StockOpeningRepository stockOpeningRepository;
	
	@Autowired
	private StockUtility stockUtility;
	
	@Autowired
	private UserCredentials userCredentials;
	
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
	public StockOpening create(OpeningStockForm openingStockForm) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(openingStockForm.getTransaction_date());
		
		String system_number = stockUtility.generateStockOpeningSystemNumber(openingStockForm.getLocation().getId(), cal.get(Calendar.YEAR));
		
		StockOpening opening = new StockOpening();
		opening.setSystemNumber(system_number);
		opening.setDocumentNumber(openingStockForm.getDocument_number());
		opening.setLocation(openingStockForm.getLocation());
		opening.setTransactionDate(openingStockForm.getTransaction_date());
		opening.setCreatedBy(userCredentials.getLoggedInUser());
		
		return stockOpeningRepository.save(opening);
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
