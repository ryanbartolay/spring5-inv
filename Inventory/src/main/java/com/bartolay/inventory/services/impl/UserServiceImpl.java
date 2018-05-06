package com.bartolay.inventory.services.impl;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.form.UserForm;
import com.bartolay.inventory.repositories.UserDatatableRepository;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService<User> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDatatableRepository userDatatableRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public JSONObject retrieveDatatableList(Map<String, String> requestMap) {
		DatatableParameter parameter = new DatatableParameter(requestMap);
		JSONArray array = userDatatableRepository.findAllData(parameter);
		long recordsTotal = userDatatableRepository.findAllCount(parameter);
		
		JSONObject object = new JSONObject();
		object.put("data", array);
		object.put("recordsTotal", recordsTotal);
		object.put("recordsFiltered", recordsTotal);
		object.put("draw", parameter.getDraw());
		
		return object;
	}

	@Override
	public User create(UserForm userForm) {
		User user = new User();
		user.setUsername(userForm.getUsername());
		user.setEmail(userForm.getEmail());
		user.setFirstName(userForm.getFirstname());
		user.setLastName(userForm.getLastname());
		
		user.setPassword(passwordEncoder.encode("123456a"));
		
		user.setType("ADMIN");
		user.setEnabled(true);
		return userRepository.save(user);
	}

	@Override
	public User update(UserForm userForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
