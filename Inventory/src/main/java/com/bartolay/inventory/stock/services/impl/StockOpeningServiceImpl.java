package com.bartolay.inventory.stock.services.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.enums.ActivityType;
import com.bartolay.inventory.form.StockOpeningForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.services.InventoryCoreService;
import com.bartolay.inventory.stock.entity.StockOpening;
import com.bartolay.inventory.stock.entity.StockOpeningItem;
import com.bartolay.inventory.stock.services.StockOpeningService;
import com.bartolay.inventory.utils.ActivityUtility;
import com.bartolay.inventory.utils.UserCredentials;

@Service
public class StockOpeningServiceImpl implements StockOpeningService {

	@Autowired
	@Qualifier("openingStockDatatableRepository")
	private DatatableRepository openingStockDatatableRepository;	
	@Autowired
	private UserCredentials userCredentials;
	@Autowired
	private InventoryCoreService inventoryService;
	
	@Autowired
	private ActivityUtility activityUtility;
	
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
	public StockOpening create(StockOpeningForm openingStockForm) throws ParseException {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		
		for(StockOpeningItem item :openingStockForm.getStockOpeningItems()) {
			System.err.println(item);
		}
		
		StockOpening opening = new StockOpening();
		opening.setDocumentNumber(openingStockForm.getDocument_number());
		opening.setLocation(openingStockForm.getLocation());
		opening.setTransactionDate(cal.getTime());
		opening.setItems(openingStockForm.getStockOpeningItems());
		opening.setYear(openingStockForm.getYear());
		opening.setDescription(openingStockForm.getDescription());
		opening.setCreatedBy(userCredentials.getLoggedInUser());
		opening = inventoryService.createStockOpening(opening);
		
		activityUtility.log(ActivityType.STOCK_OPENING, opening);
		
		return opening;
	}

	@Override
	public StockOpening update(@Valid StockOpeningForm openingStockForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockOpening delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
