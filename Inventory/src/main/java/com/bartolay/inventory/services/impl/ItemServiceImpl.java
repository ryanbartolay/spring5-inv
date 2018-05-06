package com.bartolay.inventory.services.impl;

import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.itemForm.ItemForm;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.services.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	@Qualifier("itemDatatableRepository")
	private DatatableRepository itemDatatableRepository;

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = itemDatatableRepository.findAllData(parameter);
		long recordsTotal = itemDatatableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	public Item create(ItemForm itemForm) {
		Item item = new Item();
		item.setCode(itemForm.getCode());
		item.setName(itemForm.getName());
		item.setBrand(brandRepository.findById(itemForm.getBrandId()).get());
		item.setEnabled(true);
		return itemRepository.save(item);
	}

	@Override
	public Item update(ItemForm itemForm) {
		Item item = itemRepository.findById(itemForm.getId()).get();
		item.setCode(itemForm.getCode());
		item.setName(itemForm.getName());
		item.setBrand(brandRepository.findById(itemForm.getBrandId()).get());
		return itemRepository.save(item);
	}

	@Override
	public Item delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
