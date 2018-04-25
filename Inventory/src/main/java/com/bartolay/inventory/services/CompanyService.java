package com.bartolay.inventory.services;

import java.util.List;

public interface CompanyService<E> {
	public List<E> findAll();
}
