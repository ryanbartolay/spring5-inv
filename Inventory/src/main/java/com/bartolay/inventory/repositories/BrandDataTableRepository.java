package com.bartolay.inventory.repositories;

import java.util.List;

import com.bartolay.inventory.datatable.model.DatatableParameter;

public interface BrandDataTableRepository<E> {
	public long findAllCount(DatatableParameter datatableParameter);
	public List<E> findAllData(DatatableParameter datatableParameter);
}
