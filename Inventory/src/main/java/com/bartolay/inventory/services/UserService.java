package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.AccountType;
import com.bartolay.inventory.form.UserForm;

public interface UserService<E> {
	public E create(UserForm userForm);
	public E update(UserForm userForm);
	
	public Iterable<User> retrieve();
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public E delete(Integer id);
	
	List<User> findAllSales(AccountType accountType);
	List<User> retrieveUserByTypeDatatableList(AccountType accountType, Map<String, String> requestMap);
}
