package com.bartolay.inventory.stock.services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Currency;
import com.bartolay.inventory.entity.Settings;
import com.bartolay.inventory.enums.ActivityType;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.enums.SettingsKeys;
import com.bartolay.inventory.exceptions.StockReceivedException;
import com.bartolay.inventory.form.StockReceivedForm;
import com.bartolay.inventory.repositories.CurrencyRepository;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.repositories.SettingsRepository;
import com.bartolay.inventory.services.InventoryCoreService;
import com.bartolay.inventory.stock.entity.StockReceived;
import com.bartolay.inventory.stock.services.StockReceivedService;
import com.bartolay.inventory.utils.ActivityUtility;

@Service
public class StockReceivedServiceImpl implements StockReceivedService {

	@Autowired
	private InventoryCoreService inventoryCoreService;
	
	@Autowired
	private ActivityUtility activityUtility;
	
	@Autowired
	private DateFormat dateFormat;
	
	@Autowired
	@Qualifier("stockReceivedDatatableRepository")
	private DatatableRepository stockReceivedDatatableRepository;
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Autowired
	private SettingsRepository settingsRepository;
	
	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = stockReceivedDatatableRepository.findAllData(parameter);
		long recordsTotal = stockReceivedDatatableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	@Deprecated
	public JSONObject retrieveExpensesDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = stockReceivedDatatableRepository.findAllData(parameter);
		long recordsTotal = stockReceivedDatatableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	
	@Override
	public StockReceived create(StockReceivedForm stockReceiveForm) throws StockReceivedException, ParseException {
		
		if(stockReceiveForm.getStockReceiveItems().size() <= 0) {
			throw new StockReceivedException("Required atleast 1 item.");
		}
		
		if(stockReceiveForm.getPaymentMethod() == null) {
			throw new StockReceivedException("Payment method is required");
		}
		
		if(stockReceiveForm.getPaymentMethod() != null && stockReceiveForm.getPaymentMethod().equals(PaymentMethod.CREDITCARD) 
				&& stockReceiveForm.getCreditCardDetails() == null) {
			throw new StockReceivedException("Credit Card Details must be specified");
		}
		
		if(stockReceiveForm.getSupplier() == null) {
			throw new StockReceivedException("Supplier is required");
		}
		
		Date date = dateFormat.parse(stockReceiveForm.getTransactionDate());
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		StockReceived stockReceived = new StockReceived();
		stockReceived.setLocation(stockReceiveForm.getLocation());
		stockReceived.setDocumentNumber(stockReceiveForm.getDocument_number());
		stockReceived.setStockReceiveItems(stockReceiveForm.getStockReceiveItems());
		stockReceived.setPaymentMethod(stockReceiveForm.getPaymentMethod());
		stockReceived.setStockReceiveExpenses(stockReceiveForm.getExpenses());
		stockReceived.setDiscountValue(stockReceiveForm.getDiscountValue());
		stockReceived.setYear(stockReceiveForm.getYear());
		stockReceived.setTransactionDate(cal.getTime());
		stockReceived.setSupplier(stockReceiveForm.getSupplier());
		stockReceived.setCreditCardDetails(stockReceiveForm.getCreditCardDetails());
		stockReceived.setDescription(stockReceiveForm.getDescription());
		stockReceived.setCurrency(stockReceiveForm.getCurrency());
		
		// Lets set default currency from base currency
		if(stockReceived.getCurrency() == null) {
			Settings baseCurrencySetting = settingsRepository.findById(SettingsKeys.BASE_CURRENCY.name()).get();
			Currency baseCurrency = currencyRepository.findByCode(baseCurrencySetting.getValue().toUpperCase());
			stockReceived.setCurrency(baseCurrency);
		}
		
		stockReceived.setCurrencyRate(stockReceived.getCurrency().getRate());
		stockReceived = inventoryCoreService.createStockReceive(stockReceived);
		activityUtility.log(ActivityType.STOCK_RECEIVED, stockReceived);
		
		return stockReceived;
	}

}
