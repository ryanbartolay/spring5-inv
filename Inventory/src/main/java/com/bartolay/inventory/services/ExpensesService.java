package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Country;
import com.bartolay.inventory.entity.Expense;
import com.bartolay.inventory.form.CountryForm;
import com.bartolay.inventory.form.ExpenseForm;
import com.fasterxml.jackson.databind.node.ObjectNode;

public interface ExpensesService {

	public List<Expense> findAll();
	public ObjectNode retrieveDatatableList();
	public Expense create(@Valid ExpenseForm expenseForm);
	public Expense update(@Valid ExpenseForm expenseForm);
	public Expense delete(int id);
	
}
