package com.bartolay.inventory.services;

import java.util.List;

public interface EmployeeService<E> {
	public List<E> getAll();
	public void create(E e) throws Exception;
	public void update(E e) throws Exception;
	public E getByUsername(String username);
	public E getById(Long id);
}
