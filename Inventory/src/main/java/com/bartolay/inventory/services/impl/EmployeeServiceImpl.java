package com.bartolay.inventory.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bartolay.inventory.entity.Employee;
import com.bartolay.inventory.repositories.EmployeeRepository;
import com.bartolay.inventory.services.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService<Employee> {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAll() {
		List<Employee> employees = new ArrayList<>();
		for(Employee employee : employeeRepository.findAll()) {
			employees.add(employee);
		}
		return employees;
	}
	
	@Override
	public void create(Employee user) throws Exception {
		employeeRepository.save(user);
		//userProfileRepository.save(user.getUserProfile());
	}
	
	@Override
	public void update(Employee user) {
		employeeRepository.save(user);
	}

	@Override
	public Employee getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
