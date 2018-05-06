package com.bartolay.inventory.repositories.impl;

import org.json.JSONArray;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.repositories.DatatableRepository;

@Repository
public class ItemDatatableRepositoryImpl implements DatatableRepository {

	@Override
	public long findAllCount(DatatableParameter datatableParameter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JSONArray findAllData(DatatableParameter datatableParameter) {
		// TODO Auto-generated method stub
		return null;
	}

}
