package com.bartolay.inventory.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Color;
import com.bartolay.inventory.form.ColorForm;
import com.bartolay.inventory.repositories.ColorRepository;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.services.ColorService;
import com.bartolay.inventory.utils.ServiceUtility;


@Service
public class ColorServiceImpl implements ColorService{

	@Autowired
	private ColorRepository colorRepository;
	@Autowired
	@Qualifier("colorDataTableRepository")
	private DatatableRepository colorDataTableRepository;
	
	@Override
	public List<Color> findAll() {
		return ServiceUtility.toList(colorRepository.findAll());
	}

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = colorDataTableRepository.findAllData(parameter);
		long recordsTotal = colorDataTableRepository.findAllCount(parameter);
		
		System.err.println("data : "+array);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	public Color create(@Valid ColorForm colorForm) {
		Color color = new Color();
		color.setName(colorForm.getName());
		return colorRepository.save(color);
	}

	@Override
	public Color update(@Valid ColorForm colorForm) {
		Color color = colorRepository.findById(colorForm.getId()).get();
		color.setName(colorForm.getName());;
		return colorRepository.save(color);
	}

	@Override
	public Color delete(int id) {
		Optional<Color> color = colorRepository.findById(id);
		colorRepository.deleteById(id);
		return color.get();
	}
	
	
}
