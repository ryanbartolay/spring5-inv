package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public interface UserService<E> {
	public List<E> findAll();
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public void create(E e) throws Exception;
	public void update(E e) throws Exception;
	public E getByUsername(String username);
	public E getById(Long id);
}
