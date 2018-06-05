package com.bartolay.inventory.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.enums.ActivityType;
import com.bartolay.inventory.repositories.ActivityRepository;
import com.bartolay.inventory.sales.entity.Activity;
import com.bartolay.inventory.stock.entity.StockOpening;
import com.bartolay.inventory.stock.entity.StockReceived;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ActivityUtility {
	@Autowired
	private ActivityRepository activityRepository;
	
	@Autowired
	private UserCredentials userCredentials;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Activity stripObject(ActivityType activityType, Object obj) {
		
		if(obj == null) {
			return null;
		}
		
		Activity activity = new Activity();
		activity.setActivityType(activityType.name());
		activity.setCreatedBy(userCredentials.getLoggedInUser());
		
		Map<String, Object> before = new HashMap<>();
		
		switch(activityType) {
		case CREATE_LOCATION:
			Location l = (Location)obj;
			before.put("location_id", l.getId());
			before.put("location_name", l.getName());
			break;
		case CREATE_USER:
		case SALES_INVOICE:
		case SALES_RETURN:
		case STOCK_ADJUSTMENT:
		case STOCK_OPENING:
			StockOpening so = (StockOpening) obj;
			activity.setSystemNumber(so.getSystemNumber());
			activity.setDocumentNumber(so.getDocumentNumber());		
			activity.setLocation(so.getLocation());
			
			before.put("system_number", so.getSystemNumber());
			before.put("document_number", so.getDocumentNumber());
			before.put("transaction_date", so.getTransactionDate());
			before.put("item_count", so.getItems().size());
			break;
		case STOCK_RECEIVED:
			StockReceived sr = (StockReceived) obj;
			activity.setSystemNumber(sr.getSystemNumber());
			activity.setDocumentNumber(sr.getDocumentNumber());
			activity.setLocation(sr.getLocation());
			
			before.put("system_number", sr.getSystemNumber());
			before.put("document_number", sr.getDocumentNumber());
			before.put("transaction_date", sr.getTransactionDate());
			before.put("item_count", sr.getStockReceiveItems().size());
			break;
		case STOCK_TRANSFER:
		case UPDATE_LOCATION:
		case UPDATE_USER:
		default:
			break;
		}
		
		try {
			activity.setBefore(objectMapper.writeValueAsString(before));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return activity;
	}
	
	public void log(ActivityType activityType, Object obj) {		
		saveLog(stripObject(activityType, obj));
	}
	
	private void saveLog(Activity activity) {
		activityRepository.save(activity);
	}

}
