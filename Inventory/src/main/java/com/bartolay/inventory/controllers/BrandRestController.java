package com.bartolay.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.interfaces.CRUDRestController;
import com.bartolay.inventory.repositories.BrandRepository;

@RestController
public class BrandRestController implements CRUDRestController<Brand> {

	@Autowired
	private BrandRepository brandRepository;

	@Override
	@RequestMapping(value="/ws/brands", method=RequestMethod.GET)
	public Iterable<Brand> getList() {
		return brandRepository.findAll();
	}

	@Override
	public Brand getRecordById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Brand create(Brand k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Brand update(Brand k) {
		// TODO Auto-generated method stub
		return null;
	}
}
