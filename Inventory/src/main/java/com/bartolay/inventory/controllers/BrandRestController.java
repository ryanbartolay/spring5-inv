package com.bartolay.inventory.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
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
import com.bartolay.inventory.form.BrandForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.services.BrandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RestController
public class BrandRestController {

	@Autowired
	private BrandService brandService;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private JSONObject jsonObject;
	
	@Autowired
	private JSONArray jsonArray;
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping(value="/api/brands", method=RequestMethod.GET)
	public ResponseEntity<List<Brand>> getList() {
		List<Brand> brands = new ArrayList<>();

		for(Brand brand : brandRepository.apiFindAll()) {
			brands.add(brand);
		}
		return ResponseEntity.ok(brands);
	}
	
	@RequestMapping(value="/api/datatable/brands", method=RequestMethod.GET, produces="application/json")
	public String datatableBrand(@RequestParam Map<String, String> requestMap) throws JsonProcessingException {
		System.err.println("raw request >>>>> " + requestMap);
		return objectMapper.writeValueAsString(brandService.retrieveList(requestMap));
	}
	
	@RequestMapping(value="/api/brands/{id}", method=RequestMethod.GET)
	public ResponseEntity<Brand> getById(@PathVariable Long id) {
		try {
			Brand brand = brandRepository.apiFindById(id);
			return ResponseEntity.ok(brand);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value="/api/brands", method=RequestMethod.POST)
	public ResponseEntity<ApiResponse> create(@Valid BrandForm brandForm, BindingResult bindingResult) throws RestApiException {

		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		try {
			brandService.create(brandForm);
		} catch(Exception e) {
			throw new RestApiException(e);
		}
		
		ApiResponse apiError = new ApiResponse(HttpStatus.OK, "Succesfully created brand");
		return new ResponseEntity<ApiResponse>(apiError, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/brands", method=RequestMethod.PUT)
	public ResponseEntity<Brand> update(@RequestBody Brand brand) {
		return ResponseEntity.ok(brandRepository.save(brand));
	}

	@RequestMapping(value="/api/brands/{id}", method=RequestMethod.DELETE)
	public BodyBuilder delete(@PathVariable Long id) {
		brandRepository.deleteById(id);
		return ResponseEntity.ok();
	}
}
