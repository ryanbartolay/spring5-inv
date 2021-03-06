package com.bartolay.inventory.services;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.form.ItemForm;

public interface ItemService {
	public JSONObject retrieveDatatableList(Map<String, String> requestMap);
	public Item create(ItemForm itemForm);
	public Item update(ItemForm itemForm);
	public Item delete(Integer id);
	
	public String retrieveItemUnitsById(Integer id);
	public List<Item> retrieveByLocation(Location location);
}
