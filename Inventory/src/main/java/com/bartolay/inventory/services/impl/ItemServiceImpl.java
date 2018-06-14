package com.bartolay.inventory.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Inventory;
import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.ItemUnit;
import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.form.ItemForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.repositories.InventoryRepository;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.services.ItemService;
import com.bartolay.inventory.utils.UserCredentials;


@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private InventoryRepository inventoryRepository; 

	@Autowired
	@Qualifier("itemDatatableRepository")
	private DatatableRepository itemDatatableRepository;
	
	@Autowired
	private UserCredentials userCredentials;

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = itemDatatableRepository.findAllData(parameter);
		long recordsTotal = itemDatatableRepository.findAllCount(parameter);
		
		array.forEach(obj -> {
			JSONObject o = (JSONObject) obj;
			o.put("available_units_id", o.get("available_units_id").toString().replaceAll("[ {}.\"]", ""));
			o.put("available_units_abbr", o.get("available_units_abbr").toString().replaceAll("[ {}.\"]", ""));
			o.put("available_units_name", o.get("available_units_name").toString().replaceAll("[ {}.\"]", ""));
		});
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}
	
	@Override
	public String retrieveItemUnitsById(Integer id) {
		Item item = itemRepository.findById(id).get();
		Set<ItemUnit> units = item.getItemUnits();
		
		JSONObject returnObj = new JSONObject();
		
		JSONArray arr = new JSONArray();	

		// set the default first
		JSONObject obj = new JSONObject();
		
		for(ItemUnit unit : units) {
			
			obj = new JSONObject();
			obj.put("id", unit.getId());
			obj.put("abbreviation", unit.getUnit().getAbbreviation());
			obj.put("name", unit.getUnit().getName());
			obj.put("rate", unit.getRate());
			obj.put("default", unit.getUnit().getId() == item.getDefaultUnit().getId());
			arr.put(obj);
		}
		returnObj.put("data", arr);
	
		return returnObj.toString();
	}

	@Override
	public Item create(ItemForm itemForm) {
		Item item = new Item();
		item.setCode(itemForm.getCode());
		item.setName(itemForm.getName());
		item.setBrand(itemForm.getBrand());
		item.setCategory(itemForm.getCategory());
		item.setColor(itemForm.getColor());
		item.setCreatedBy(userCredentials.getLoggedInUser());
		item.setEnabled(true);
		item.setDefaultUnit(itemForm.getUnit());
		item.setItemUnits(itemForm.getItemUnits());
		item.setModel(itemForm.getModel());
		item.setSize(itemForm.getItemSize());
		item.setCountry(itemForm.getCountry());
		item.setSupplier(itemForm.getSupplier());
		return itemRepository.save(item);
	}

	@Override
	public Item update(ItemForm itemForm) {
		Item item = itemRepository.findById(itemForm.getId()).get();

		item.setName(itemForm.getName());
		item.setBrand(itemForm.getBrand());
		item.setCategory(itemForm.getCategory());
		item.setColor(itemForm.getColor());
		item.setUpdatedBy(userCredentials.getLoggedInUser());
		item.setDefaultUnit(itemForm.getUnit());
		item.setItemUnits(itemForm.getItemUnits());
		item.setModel(itemForm.getModel());
		item.setSize(itemForm.getItemSize());
		item.setCountry(itemForm.getCountry());
		item.setSupplier(itemForm.getSupplier());
		return itemRepository.save(item);
	}

	@Override
	public Item delete(Integer id) {
		Optional<Item> item = itemRepository.findById(id);
		
		itemRepository.deleteById(id);
		return item.get();
	}

	@Override
	public List<Item> retrieveByLocation(Location location) {
		List<Item> items = new ArrayList<>();
		
		List<Inventory> inventories = inventoryRepository.findByLocation(location);
		if(inventories.size() > 0 ) {
			inventories.forEach(inventory -> items.add(inventory.getItem()));
		}
		
		return items;
	}

}
