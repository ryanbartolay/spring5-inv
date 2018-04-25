package com.bartolay.inventory.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.services.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService<User> {
	
	@Autowired
	private UserRepository employeeRepository;
	
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
