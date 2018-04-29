package com.bartolay.inventory.controllers;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.form.BrandForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.repositories.BrandRepository;
import com.bartolay.inventory.services.BrandService;

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

	@RequestMapping(value="/api/brands", method=RequestMethod.GET)
	public ResponseEntity<List<Brand>> getList() {
		List<Brand> brands = new ArrayList<>();

		for(Brand brand : brandRepository.apiFindAll()) {
			brands.add(brand);
		}
		return ResponseEntity.ok(brands);
	}
	
	@RequestMapping(value="/api/dt/brands", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<String> getDTList() {
		
		List<Brand> brands = new ArrayList<>();
		
		JSONArray mainArr = new JSONArray();
		for(Brand brand : brandRepository.apiFindAll()) {
			JSONArray array = new JSONArray();
			array.put(brand.getName());
			array.put(brand.getCompany().getName());
			
			mainArr.put(array);
			brands.add(brand);
		}
		
		jsonObject.put("data", mainArr);
		return ResponseEntity.ok(jsonObject.toString());
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
			Brand brand = brandService.create(brandForm);
			
			ApiResponse apiError = new ApiResponse(HttpStatus.OK, "Succesfully Created brand "+ brand.getName());
			return new ResponseEntity<ApiResponse>(apiError, HttpStatus.OK);
		} catch(Exception e) {
			throw new RestApiException(e);
		}
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
