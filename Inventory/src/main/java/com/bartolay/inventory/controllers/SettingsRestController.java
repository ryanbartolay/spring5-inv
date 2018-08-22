package com.bartolay.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.entity.Currency;
import com.bartolay.inventory.repositories.CurrencyRepository;

@RestController
public class SettingsRestController {
	
	@Autowired
	private CurrencyRepository currencyRepository;
	
	public Iterable<Currency> retrieveAll() {
		return currencyRepository.findAll();
	}

}
