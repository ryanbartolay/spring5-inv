package com.bartolay.inventory.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.datatable.model.SortOrder;
import com.bartolay.inventory.entity.Color;
import com.bartolay.inventory.form.ColorForm;
import com.bartolay.inventory.repositories.ColorRepository;
import com.bartolay.inventory.services.ColorService;
import com.bartolay.inventory.utils.ServiceUtility;


@Service
public class ColorServiceImpl implements ColorService{

	@Autowired
	private ColorRepository colorRepository;
	
	@Override
	public List<Color> findAll() {
		return ServiceUtility.toList(colorRepository.findAll());
	}

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		System.err.println(parameter);
		List<Color> colors;
		if(parameter.getSortOrder().equals(SortOrder.ASC)) {
			colors = colorRepository.findAllByOrderByColorAsc();
		}else {
			colors = colorRepository.findAllByOrderByColorDesc();
		}
		long recordsTotal = colorRepository.count();
		JSONObject object = new JSONObject();
		object.put("data", colors);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		return object;
	}

	@Override
	public Color create(@Valid ColorForm colorForm) {
		Color color = new Color();
		color.setCode(colorForm.getCode());
		color.setColor(colorForm.getColor());
		return colorRepository.save(color);
	}

	@Override
	public Color update(@Valid ColorForm colorForm) {
		Color color = colorRepository.findById(colorForm.getId()).get();
		color.setCode(colorForm.getCode());
		color.setColor(colorForm.getColor());
		return colorRepository.save(color);
	}

	@Override
	public Color delete(Long id) {
		Optional<Color> color = colorRepository.findById(id);
		colorRepository.deleteById(id);
		return color.get();
	}
	
	
}
