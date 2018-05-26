package com.bartolay.inventory.services.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.AccountType;
import com.bartolay.inventory.form.UserForm;
import com.bartolay.inventory.repositories.DatatableRepository;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.services.UserService;
import com.bartolay.inventory.utils.UserCredentials;

@Service
@Transactional
public class UserServiceImpl implements UserService<User> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("userDatatableRepository")
	private DatatableRepository userDatatableRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserCredentials userCredentials;
	
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
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		
		user.setPassword(passwordEncoder.encode("123456a"));
		
		user.setAccountType(AccountType.ADMIN);
		user.setEnabled(true);
		return userRepository.save(user);
	}

	@Override
	public User update(UserForm userForm) {
		User user = userRepository.findById(userForm.getId()).get();
		user.setEmail(userForm.getEmail());
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		return userRepository.save(user);
	}
	
	@Override
	public User delete(Integer id) {

		Optional<User> user = userRepository.findById(id);
		
		if(userCredentials.getLoggedInUser().getUsername().equals(user.get().getUsername())) {
			throw new RuntimeException("Unable to delete your own account.");
		}
		
		userRepository.deleteById(id);
		return user.get();
	}

	@Override
	public List<User> findAllSales() {
		return userRepository.findAllByAccountType(AccountType.USER);
	}

	@Override
	public Iterable<User> retrieve() {
		return userRepository.findAll();
	}

	
}
