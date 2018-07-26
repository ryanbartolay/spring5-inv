package com.bartolay.inventory.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.entity.Expense;
import com.bartolay.inventory.exceptions.StockReceivedException;
import com.bartolay.inventory.form.ExpenseForm;
import com.bartolay.inventory.repositories.ExpenseRepository;
import com.bartolay.inventory.services.ExpenseService;
import com.bartolay.inventory.stock.entity.StockReceived;
import com.bartolay.inventory.stock.repositories.StockReceivedRepository;
import com.bartolay.inventory.utils.ServiceUtility;
import com.bartolay.inventory.utils.UserCredentials;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class ExpensesServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private UserCredentials userCredentials;
	@Autowired
	private StockReceivedRepository stockReceivedRepository;

	@Override
	public Iterable<Expense> retrieveList() {
		return expenseRepository.apiFindAll();
	}
	
	@Override
	public ObjectNode retrieveDatatableList() {
		List<Expense> array = ServiceUtility.toList(expenseRepository.findAll());
		ObjectNode object = objectMapper.createObjectNode();
		object.set("data", objectMapper.convertValue(array, JsonNode.class));
		object.put("recordsTotal", array.size());
		object.put("recordsFiltered", array.size());
		object.put("draw", 0);
		
		return object;
	}

	@Override
	public JSONObject create(@Valid ExpenseForm expenseForm) {
		Expense expense = new Expense();		
		expense.setCode(expenseForm.getDescription().trim().replace(" ", "_").toUpperCase());
		expense.setDescription(expenseForm.getDescription());
		expense.setCreatedBy(userCredentials.getLoggedInUser());
		
		expense = expenseRepository.save(expense);
		
		JSONObject obj = new JSONObject();
		obj.put("status", "OK");
		obj.put("id", expense.getId());
		obj.put("code", expense.getCode());
		obj.put("description", expense.getDescription());
		
		return obj;
	}

	@Override
	public Expense update(@Valid ExpenseForm expenseForm) {
		Expense expense = expenseRepository.findById(expenseForm.getId()).get();
		expense.setDescription(expenseForm.getDescription());
		expense.setUpdatedBy(userCredentials.getLoggedInUser());
		expense.setUpdatedDated(new Date());
		expenseRepository.save(expense);
		return expense;
	}

	@Override
	public JSONObject delete(Integer id) throws StockReceivedException {
		Expense expense = new Expense(id);
		List<StockReceived> stockReceives = stockReceivedRepository.findAllByExpense(expense);
		
		if(stockReceives.size() > 0) {
			String sql = "Unable to delete this reason. You must delete first these Stock Received";
			
			List<String> errors = new ArrayList<>();
			for(StockReceived received : stockReceives) {
				errors.add(received.getSystemNumber() + " " + (received.getDocumentNumber() == null ? "" : received.getDocumentNumber()));
			}
			
			throw new StockReceivedException(sql,errors);
		}
		
		expenseRepository.delete(expense);
		JSONObject obj = new JSONObject();
		obj.put("status", "OK");
		obj.put("localizedMessage", "Expense deleted");
		return obj;
	}

}
