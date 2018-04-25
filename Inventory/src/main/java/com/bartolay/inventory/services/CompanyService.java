package com.bartolay.inventory.services;

import java.util.List;

public interface CompanyService<E> extends ServiceUtility {
	public List<E> findAll();
}
