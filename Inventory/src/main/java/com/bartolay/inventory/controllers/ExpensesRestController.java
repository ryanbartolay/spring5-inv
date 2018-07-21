package com.bartolay.inventory.controllers;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.entity.Expense;
import com.bartolay.inventory.form.ExpenseForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.services.ExpensesService;
import com.bartolay.inventory.stock.controllers.AbstractRestController;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ExpensesRestController extends AbstractRestController{

	@Autowired
	private ExpensesService expensesService;
	@Autowired
	private StringUtils stringUtils;
	
	@RequestMapping(value="/api/datatable/expenses", method=RequestMethod.GET, produces="application/json")
	public String datatable() throws JsonProcessingException {
		return expensesService.retrieveDatatableList().toString();
	}
	
	@RequestMapping(value="/api/expenses", method=RequestMethod.POST)
	public String create(@Valid ExpenseForm expenseForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}
		ApiResponse response = null;
		
		try {
			Expense expense = expensesService.create(expenseForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully created " + expense.getDescription());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/expenses", method=RequestMethod.PUT)
	public String update(@Valid ExpenseForm expenseForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		
		if (bindingResult.hasErrors()) {
			return handleRestApiException(bindingResult);
		}

		try {
			Expense expense = expensesService.update(expenseForm);
			response = new ApiResponse(HttpStatus.OK, "Succesfully updated " + expense.getDescription());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/expenses/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Integer id) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		try {
			Expense expense = expensesService.delete(id);

			response = new ApiResponse(HttpStatus.ACCEPTED, "Record deleted " + expense.getDescription());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			
		}
		return stringUtils.encode(response);
	}
	
}
