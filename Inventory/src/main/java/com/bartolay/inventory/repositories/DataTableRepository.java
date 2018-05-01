package com.bartolay.inventory.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bartolay.inventory.datatable.model.DatatableParameter;

@Repository
public interface DataTableRepository<E> {
	public List<E> findAll(DatatableParameter parameter);
}
