package com.bartolay.inventory.services.impl;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Country;
import com.bartolay.inventory.form.CountryForm;
import com.bartolay.inventory.repositories.CountryRepository;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.services.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	@Qualifier("countryDatatableRepository")
	private DatatableRepository countryDatatableRepository;
	@Override
	public List<Country> findAll() {
		return null;
	}

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = countryDatatableRepository.findAllData(parameter);
		long recordsTotal = countryDatatableRepository.findAllCount(parameter);
		
		System.err.println("data : "+array);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	public Country create(@Valid CountryForm countryForm) {
		Country country = new Country();
		country.setAbbreviation(countryForm.getAbbreviation());
		country.setName(countryForm.getName());
		return countryRepository.save(country);
	}

	@Override
	public Country update(@Valid CountryForm countryForm) {
		Country country = countryRepository.findById(countryForm.getId()).get();

		System.err.println(country);
		if(country != null) {
			country.setAbbreviation(countryForm.getAbbreviation());
			country.setName(countryForm.getName());
			countryRepository.save(country);
		}
		return country;
	}

	@Override
	public Country delete(int id) {
		Country country = countryRepository.findById(id).get();
		countryRepository.deleteById(id);
		return country;
	}

}
