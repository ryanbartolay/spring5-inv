package com.bartolay.inventory.services.impl;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.UserGroup;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.repositories.UserGroupRepository;
import com.bartolay.inventory.services.UserGroupService;

@Service
public class UserGroupServiceImpl implements UserGroupService{

	@Autowired
	@Qualifier("userGroupDatatableRepository")
	private DatatableRepository userGroupDatatableRepository;
	
	@Autowired
	private UserGroupRepository userGroupRepository;
	
	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray data = userGroupDatatableRepository.findAllData(parameter);
		long recordsTotal = userGroupDatatableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", data);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	public UserGroup retrieveByName(String name) {
//		UserGroup userGroup = userGroupRepository.findByName(name);
		
		System.err.println("user group");
//		System.err.println(userGroup);
		
		return null;
	}

}
