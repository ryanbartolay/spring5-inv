package com.bartolay.inventory.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Unit;
import com.bartolay.inventory.form.UnitForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.repositories.UnitRepository;
import com.bartolay.inventory.services.UnitService;
import com.bartolay.inventory.utils.ServiceUtility;
import com.bartolay.inventory.utils.UserCredentials;

@Service
@Transactional
public class UnitServiceImpl implements UnitService {

	@Autowired
	private UnitRepository unitRepository;

	@Autowired
	private UserCredentials userCredentials;

	@Autowired
	@Qualifier("unitDatatableRepository")
	private DatatableRepository unitDatatableRepository;

	@Override
	public Unit create(UnitForm unitForm) {

		Unit unit = new Unit();
		unit.setAbbreviation(unitForm.getAbbreviation());
		unit.setName(unitForm.getName());
		unit.setCreatedBy(userCredentials.getLoggedInUser());
		unit.setCreatedDate(new Date());
		return unitRepository.save(unit);
	}
	
	@Override
	public Unit update(UnitForm unitForm) {
		Unit unit = unitRepository.findById(unitForm.getId()).get();
		if(unit != null) {
			unit.setAbbreviation(unitForm.getAbbreviation());
			unit.setName(unitForm.getName());
			unit.setUpdatedBy(userCredentials.getLoggedInUser());
			unit.setUpdatedDated(new Date());
			unitRepository.save(unit);
		}
		return unit;
	}


	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {

		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = unitDatatableRepository.findAllData(parameter);
		long recordsTotal = unitDatatableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	public Unit delete(Integer id) {
		Optional<Unit> unit = unitRepository.findById(id);
		unitRepository.deleteById(id);
		
		System.err.println(unit.get());
		
		return unit.get();
	}

	@Override
	public List<Unit> findAll() {
		return ServiceUtility.toList(unitRepository.findAll());
	}
}
