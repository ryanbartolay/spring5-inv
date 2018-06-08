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
import com.bartolay.inventory.entity.Model;
import com.bartolay.inventory.form.ModelForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.repositories.ModelRepository;
import com.bartolay.inventory.services.ModelService;

@Service
public class ModelServiceImpl implements ModelService{

	@Autowired
	@Qualifier("modelDatatableRepository")
	private DatatableRepository modelDatatableRepository;
	@Autowired
	private ModelRepository modelRepository;
	
	@Override
	public List<Model> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = modelDatatableRepository.findAllData(parameter);
		long recordsTotal = modelDatatableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	public Model create(@Valid ModelForm modelForm) {
		Model model = new Model();
		model.setBrand(modelForm.getBrand());
		model.setDescription(modelForm.getDescription());
		model.setName(modelForm.getName());
		System.err.println(model);
		return modelRepository.save(model);
	}

	@Override
	public Model update(@Valid ModelForm modelForm) {
		Model model = modelRepository.findById(modelForm.getId()).get();
		
		System.err.println(model + "update");
		
		if(model != null) {
			model.setBrand(modelForm.getBrand());
			model.setDescription(modelForm.getDescription());
			model.setName(modelForm.getName());
			model = modelRepository.save(model);
		}
		return model;
	}

	@Override
	public Model delete(Long id) {
		Optional<Model> model = modelRepository.findById(id);
		if(model.isPresent()) {
			modelRepository.delete(model.get());
		}
		return model.get();
	}

}
