package com.bartolay.inventory.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.repositories.BrandRepository;

@RestController
public class BrandRestController {

	@Autowired
	private BrandRepository brandRepository;

	@RequestMapping(value="/api/brands", method=RequestMethod.GET)
	public ResponseEntity<List<Brand>> getList() {
		List<Brand> brands = new ArrayList<>();
		
		for(Brand brand : brandRepository.apiFindAll()) {
			brands.add(brand);
		}
		return ResponseEntity.ok(brands);
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
	public ResponseEntity<Brand> create(@RequestBody Brand brand) {
		return ResponseEntity.ok(brandRepository.save(brand));
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