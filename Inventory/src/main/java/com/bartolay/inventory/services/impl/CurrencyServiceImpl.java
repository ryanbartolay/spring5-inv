package com.bartolay.inventory.services.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.entity.Currency;
import com.bartolay.inventory.exceptions.CurrencyException;
import com.bartolay.inventory.form.CurrencyForm;
import com.bartolay.inventory.repositories.CurrencyRepository;
import com.bartolay.inventory.services.CurrencyService;
import com.bartolay.inventory.stock.entity.StockReceived;
import com.bartolay.inventory.stock.repositories.StockReceivedRepository;
import com.bartolay.inventory.utils.ServiceUtility;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private StockReceivedRepository stockReceivedRepository;
	
	@Autowired
	private StringUtils stringUtils;

	@Override
	public Iterable<Currency> retrieveList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectNode retrieveDatatableList(Map<String, String> requestMap)
			throws JsonProcessingException, UnsupportedEncodingException {
		List<Currency> currencies = ServiceUtility.toList(currencyRepository.findAll());

		for (Currency currency : currencies) {
			currency.setSymbol(stringUtils.encode(currency.getSymbol()));
		}
		
		
		Collections.sort(currencies, new Comparator<Currency>() {

			@Override
			public int compare(Currency o1, Currency o2) {
				if(o1.getName().compareTo(o2.getName()) > 1) {
					return 1;
				} else if(o1.getName().compareTo(o2.getName()) < 1) {
					return -1;
				}
				return 0;
			}
		});

		ObjectNode object = objectMapper.createObjectNode();
		object.set("data", objectMapper.convertValue(currencies, JsonNode.class));
		object.put("recordsTotal", currencies.size());
		object.put("recordsFiltered", currencies.size());
		object.put("draw", 0);
		return object;
	}

	@Override
	public JSONObject create(@Valid CurrencyForm currencyForm) throws CurrencyException {
		if (currencyForm.getCurrency_name() == null) {
			throw new CurrencyException("Name is required");
		}

		
		System.err.println("create cyrrebct'");
		Currency c = new Currency();
		c.setName(currencyForm.getCurrency_name());
		c.setCode(currencyForm.getCurrency_code());
		c.setRate(currencyForm.getCurrency_rate());
		c.setSymbol(currencyForm.getCurrency_symbol());
		c.setDecimalPlaces(currencyForm.getCurrency_decimalPlaces());
		
		c = currencyRepository.save(c);

		JSONObject obj = new JSONObject();
		obj.put("status", "OK");
		obj.put("id", c.getId());
		obj.put("name", c.getName());

		return obj;
	}

	@Override
	public JSONObject update(@Valid CurrencyForm currencyForm) throws CurrencyException {
		if (currencyForm.getCurrency_name() == null) {
			throw new CurrencyException("Name is required");
		}

		System.err.println("update cyrrebct'");
		Currency c = new Currency();
		c.setId(currencyForm.getCurrency_id());
		c.setName(currencyForm.getCurrency_name());
		c.setCode(currencyForm.getCurrency_code());
		c.setRate(currencyForm.getCurrency_rate());
		c.setSymbol(currencyForm.getCurrency_symbol());
		c.setDecimalPlaces(currencyForm.getCurrency_decimalPlaces());
		
		c = currencyRepository.save(c);

		JSONObject obj = new JSONObject();
		obj.put("status", "OK");
		obj.put("id", c.getId());
		obj.put("name", c.getName());

		return obj;
	}

	@Override
	public JSONObject delete(Integer id) throws CurrencyException {
		
		Currency currency = new Currency();
		currency.setId(id);
		
		List<StockReceived> stockReceiveds = stockReceivedRepository.findAllByCurrency(currency);
		
		if(stockReceiveds.size() > 0) {
			String message = "Unable to delete this reason. You must delete first these Stock Received.";

			List<String> errors = new ArrayList<>();
			for(StockReceived stockReceived : stockReceiveds) {
				errors.add(stockReceived.getSystemNumber() + " " + stockReceived.getDocumentNumber() + "\n");
			}

			throw new CurrencyException(message,errors);
		}
		
		currencyRepository.delete(currency);
		JSONObject obj = new JSONObject();
		obj.put("status", "OK");
		obj.put("localizedMessage", "Currency deleted");
		return obj;
	}

	@Override
	public Currency findById(Integer currencyId) throws JsonProcessingException, UnsupportedEncodingException {
		Optional<Currency> optCurrency = currencyRepository.findById(currencyId);
		
		if(optCurrency.isPresent()) {
			Currency currency = optCurrency.get();
			currency.setSymbol(stringUtils.encode(currency.getSymbol()));
			return currency;
		}
		
		return null;
	}

}
