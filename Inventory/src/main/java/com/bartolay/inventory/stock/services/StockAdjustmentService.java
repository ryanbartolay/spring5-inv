package com.bartolay.inventory.stock.services;

import java.text.ParseException;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.exceptions.StockAdjustmentException;
import com.bartolay.inventory.form.StockAdjustmentForm;
import com.bartolay.inventory.form.StockAdjustmentReasonForm;
import com.bartolay.inventory.stock.entity.StockAdjustment;
import com.bartolay.inventory.stock.entity.StockAdjustmentReason;

public interface StockAdjustmentService {

	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public StockAdjustment create(StockAdjustmentForm stockAdjustmentForm) throws ParseException, StockAdjustmentException;
	
	// Stock Adjustment Reasons
	public Iterable<StockAdjustmentReason> retrieveReasonList();
	public JSONObject createAdjustmentReason(StockAdjustmentReasonForm reason) throws StockAdjustmentException;
	public JSONObject deleteAdjustmentReason(StockAdjustmentReason reason) throws StockAdjustmentException;
	
}
