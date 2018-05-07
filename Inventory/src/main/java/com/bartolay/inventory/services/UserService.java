package com.bartolay.inventory.services;

import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.form.UserForm;

public interface UserService<E> {
	public E create(UserForm userForm);
	public E update(UserForm userForm);
	
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public E delete(Long id);
}