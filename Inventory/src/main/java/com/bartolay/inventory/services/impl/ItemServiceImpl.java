package com.bartolay.inventory.services.impl;

import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.itemForm.ItemForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.services.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	@Qualifier("itemDatatableRepository")
	private DatatableRepository itemDatatableRepository;

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item create(ItemForm itemForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item update(ItemForm itemForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
