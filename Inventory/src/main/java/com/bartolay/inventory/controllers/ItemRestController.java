package com.bartolay.inventory.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.entity.Item;
import com.bartolay.inventory.form.ItemForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.repositories.ItemRepository;
import com.bartolay.inventory.services.ItemService;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class ItemRestController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private StringUtils stringUtils;
	
	@RequestMapping(value="/api/datatable/items", method=RequestMethod.GET, produces="application/json")
	public String datatableBrand(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		return itemService.retrieveDatatableList(requestMap).toString();
	}

	@RequestMapping(value="/api/items/{id}", method=RequestMethod.GET)
	public String getById(@PathVariable Integer id) {
		try {
			Item item = itemRepository.apiFindById(id);	
			return stringUtils.encode(item);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/api/items", method=RequestMethod.POST)
	public String create(@Valid ItemForm itemForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {
		System.err.println(itemForm.getItemSize());
		
		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		ApiResponse response = null;
		System.err.println("getItemUnits : "+itemForm.getItemUnits());
		try {
			Item item = itemService.create(itemForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully created " + item.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/items", method=RequestMethod.PUT)
	public String update(@Valid ItemForm itemForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		
		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		try {
			Item item = itemService.update(itemForm);
			response = new ApiResponse(HttpStatus.OK, "Succesfully updated " + item.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/items/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Integer id) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		try {
			Item item = itemService.delete(id);

			response = new ApiResponse(HttpStatus.ACCEPTED, "Record deleted " + item.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			
		}
		return stringUtils.encode(response);
	}
}
