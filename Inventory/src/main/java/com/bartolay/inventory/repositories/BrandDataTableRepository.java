package com.bartolay.inventory.repositories;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.pagination.DataTableResults;

public interface BrandDataTableRepository<E> {
	public DataTableResults<E> findAll(DatatableParameter datatableParameter);
}
