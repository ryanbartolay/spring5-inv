package com.bartolay.inventory.services;

import javax.validation.Valid;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Expense;
import com.bartolay.inventory.form.ExpenseForm;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ExpenseService {

	public Iterable<Expense> retrieveList();
	public ObjectNode retrieveDatatableList();
	public JSONObject create(@Valid ExpenseForm expenseForm);
	public Expense update(@Valid ExpenseForm expenseForm);
	public Expense delete(int id);
	
}
