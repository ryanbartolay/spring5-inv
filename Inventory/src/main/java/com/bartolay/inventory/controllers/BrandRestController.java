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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class BrandRestController {

	@Autowired
	private BrandService<Brand> brandService;

	@Autowired
	private BrandRepository brandRepository;

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private StringUtils stringUtils;

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
		return brandService.retrieveDatatableList(requestMap).toString();
	}

	@RequestMapping(value="/api/brands/{id}", method=RequestMethod.GET)
	public String getById(@PathVariable Long id) {
		try {
			Brand brand = brandRepository.apiFindById(id);

			return stringUtils.encode(brand);
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
			Brand brand = brandService.create(brandForm);

			ApiResponse apiError = new ApiResponse(HttpStatus.OK, "Succesfully created " + brand.getName());
			return new ResponseEntity<ApiResponse>(apiError, HttpStatus.OK);
		} catch(Exception e) {
			throw new RestApiException(e);
		}


	}

	@RequestMapping(value="/api/brands", method=RequestMethod.PUT)
	public ResponseEntity<ApiResponse> update(@Valid BrandForm brandForm, BindingResult bindingResult) throws RestApiException {

		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		try {
			Brand brand = brandService.update(brandForm);
			ApiResponse apiError = new ApiResponse(HttpStatus.OK, "Succesfully updated " + brand.getName());
			return new ResponseEntity<ApiResponse>(apiError, HttpStatus.OK);
		} catch(Exception e) {
			throw new RestApiException(e);
		}
	}

	@RequestMapping(value="/api/brands/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Long id) throws RestApiException {
		try {
			Brand brand = brandService.delete(id);
			ApiResponse response = new ApiResponse(HttpStatus.OK, "Record deleted " + brand.getName());
			return stringUtils.encode(response);
		} catch(Exception e) {
			throw new RestApiException(e);
		}
	}
}
