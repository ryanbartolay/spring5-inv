package com.bartolay.inventory.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.entity.Category;
import com.bartolay.inventory.form.BrandForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.repositories.BrandRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class CategoryRestController {
	
	@Autowired
	private BrandRepository brandRepository;
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	@RequestMapping(value="/api/categories", method=RequestMethod.GET)
	public ResponseEntity<List<Category>> getList() {
		List<Category> brands = new ArrayList<>();

//		for(Brand brand : brandRepository.apiFindAll()) {
//			brands.add(brand);
//		}
		return ResponseEntity.ok(brands);
	}
	
	@RequestMapping(value="/api/datatable/categories", method=RequestMethod.GET, produces="application/json")
	public String datatable(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		System.err.println("raw request >>>>> " + requestMap);
//		return brandService.retrieveList(requestMap).toString();
		return null;
	}
	
	@RequestMapping(value="/api/categories/{id}", method=RequestMethod.GET)
	public ResponseEntity<Category> getById(@PathVariable Long id) {
//		try {
//			Brand brand = brandRepository.apiFindById(id);
//			return ResponseEntity.ok(brand);
//		} catch(Exception e) {
//			e.printStackTrace();
//			return null;
//		}
		return null;
	}

	@RequestMapping(value="/api/categories", method=RequestMethod.POST)
	public ResponseEntity<ApiResponse> create(@Valid BrandForm brandForm, BindingResult bindingResult) throws RestApiException {

		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		try {
//			brandService.create(brandForm);
		} catch(Exception e) {
			throw new RestApiException(e);
		}
		
		ApiResponse apiError = new ApiResponse(HttpStatus.OK, "Succesfully created brand");
		return new ResponseEntity<ApiResponse>(apiError, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/categories", method=RequestMethod.PUT)
	public ResponseEntity<Category> update(@RequestBody Brand brand) {
//		return ResponseEntity.ok(brandRepository.save(brand));
		return null;
	}

	@RequestMapping(value="/api/categories/{id}", method=RequestMethod.DELETE)
	public BodyBuilder delete(@PathVariable Long id) {
		brandRepository.deleteById(id);
		return ResponseEntity.ok();
	}
}
