package com.bartolay.inventory.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.repositories.UserDatatableRepository;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService<User> {
	
	@Autowired
	private UserRepository employeeRepository;
	
	@Autowired
	private UserDatatableRepository userDatatableRepository;
	
	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		System.err.println("xxxxx");
		JSONArray array = userDatatableRepository.findAllData(parameter);
		System.err.println("yyyyy");
		long recordsTotal = userDatatableRepository.findAllCount(parameter);
		System.err.println("zzzzz");
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}
	
	@Override
	public List<User> findAll() {
		List<User> employees = new ArrayList<>();
		for(User employee : employeeRepository.findAll()) {
			employees.add(employee);
		}
		return employees;
	}
	
	@Override
	public void create(User user) throws Exception {
		employeeRepository.save(user);
		//userProfileRepository.save(user.getUserProfile());
	}
	
	@Override
	public void update(User user) {
		employeeRepository.save(user);
	}

	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
