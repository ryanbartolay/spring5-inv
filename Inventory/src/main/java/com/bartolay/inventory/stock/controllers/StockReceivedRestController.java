package com.bartolay.inventory.stock.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.entity.Expense;
import com.bartolay.inventory.exceptions.StockAdjustmentException;
import com.bartolay.inventory.exceptions.StockReceivedException;
import com.bartolay.inventory.form.ExpenseForm;
import com.bartolay.inventory.form.StockReceivedForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.services.ExpenseService;
import com.bartolay.inventory.stock.entity.StockReceived;
import com.bartolay.inventory.stock.services.StockReceivedService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping(value="/api")
public class StockReceivedRestController extends AbstractRestController {
	
	@Autowired
	private StockReceivedService stockReceivedService;

	@Autowired
	private ExpenseService expenseService;
	
	@RequestMapping(value="/datatable/stockreceived", method=RequestMethod.GET, produces="application/json")
	public String datatableColor(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return stockReceivedService.retrieveDatatableList(requestMap).toString();
	}
	
	@RequestMapping(value="/datatable/stock/expenses", method=RequestMethod.GET, produces="application/json")
	public String datatableExpenses(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return stockReceivedService.retrieveExpensesDatatableList(requestMap).toString();
	}
	
	@RequestMapping(value="/stock/received", method=RequestMethod.POST)
	public String create(@Valid StockReceivedForm stockReceivedForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}
		ApiResponse response = null;
		try {
			StockReceived stockReceived = stockReceivedService.create(stockReceivedForm);
			response = new ApiResponse(HttpStatus.OK, "Succesfully created Document " + stockReceived.getDocumentNumber());
		} catch(Exception e) {
			e.printStackTrace();
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
//			throw new RestApiException(e);
		}

		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/stock/received/expenses", method=RequestMethod.GET, produces="application/json")
	public String apiExpensesList(@RequestParam Map<String, String> requestMap) throws JsonProcessingException, UnsupportedEncodingException {
		return stringUtils.encode(expenseService.retrieveList());
	}
	
	@RequestMapping(value="/stock/received/expenses", method=RequestMethod.POST)
	public String createExpense(@Valid ExpenseForm expenseForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}
		ApiResponse response = null;
		
		try {
			return expenseService.create(expenseForm).toString();
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/stock/received/expenses/{id}", method=RequestMethod.DELETE, produces="application/json")
	public String deleteExpense(@PathVariable("id") Integer id) throws StockAdjustmentException, JsonProcessingException, UnsupportedEncodingException {

		Expense expense = new Expense();
		expense.setId(id);
		
		ApiResponse response = null;
		
		try {
			JSONObject obj = expenseService.delete(id);
			response = new ApiResponse(HttpStatus.OK, obj.get("localizedMessage").toString()); 
		} catch(StockReceivedException e) {			
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage(), e.getErrors());
		} catch(Exception e) {
			e.printStackTrace();
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		return stringUtils.encode(response);
		
	}
}
