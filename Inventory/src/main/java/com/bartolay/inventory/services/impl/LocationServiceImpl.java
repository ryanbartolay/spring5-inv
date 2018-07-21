package com.bartolay.inventory.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.components.BeanComponent;
import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.form.LocationForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.repositories.LocationRepository;
import com.bartolay.inventory.services.LocationService;
import com.bartolay.inventory.utils.ServiceUtility;
import com.bartolay.inventory.utils.UserCredentials;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
@Transactional
public class LocationServiceImpl extends BeanComponent implements LocationService {

	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private UserCredentials userCredentials;

	@Autowired
	@Qualifier("locationDatatableRepository")
	private DatatableRepository locationDatatableRepository;

	@Override
	public Location create(LocationForm locationForm) {
		Location location = new Location();
		location.setAbbreviation(locationForm.getAbbreviation());
		location.setEnabled(true);
		location.setFax(locationForm.getFax());
		location.setName(locationForm.getName());
		location.setTelephone(locationForm.getTelephone());
		location.setMobile(locationForm.getMobile());
		location.setAddress(locationForm.getAddress());
		location.setAddressCity(locationForm.getAddressCity());
		location.setAddressCountry(locationForm.getAddressCountry());
		location.setCreatedBy(userCredentials.getLoggedInUser());
		location.setWebsite(locationForm.getWebsite());
		return locationRepository.save(location);
	}
	
	@Override
	public Location update(LocationForm locationForm) {
		Location location = new Location();
		location.setId(locationForm.getId());
		location.setAbbreviation(locationForm.getAbbreviation());
		location.setAddress(locationForm.getAddress());
		location.setEnabled(true);
		location.setFax(locationForm.getFax());
		location.setName(locationForm.getName());
		location.setTelephone(locationForm.getTelephone());
		location.setAddressCity(locationForm.getAddressCity());
		location.setAddressCountry(locationForm.getAddressCountry());
		location.setUpdatedBy(userCredentials.getLoggedInUser());
		return locationRepository.save(location);
	}


	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {

		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = locationDatatableRepository.findAllData(parameter);
		long recordsTotal = locationDatatableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	public Location delete(Integer id) {
		Optional<Location> location = locationRepository.findById(id);
		locationRepository.deleteById(id);
		return location.get();
	}

	@Override
	public List<Location> findAll() {
		return ServiceUtility.toList(locationRepository.findAll());
	}

	@Override
	public Location findOne(Integer id) {
		return locationRepository.findById(id).get();
	}

	@Override
	public ObjectNode findAllWithPage(String query) {
		ObjectNode data = objectMapper.createObjectNode();
		ArrayNode nodes = objectMapper.createArrayNode();
		List<Location> locations = locationRepository.findByNameContaining(query);
		for (Location location : locations) {
			ObjectNode node = objectMapper.createObjectNode();
			node.put("id", location.getId());
			node.put("text", location.getName());
			nodes.add(node);
		}
		data.set("results", nodes);
		return data;
	}
}
