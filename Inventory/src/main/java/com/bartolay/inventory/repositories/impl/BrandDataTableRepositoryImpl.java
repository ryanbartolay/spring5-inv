package com.bartolay.inventory.repositories.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bartolay.inventory.components.BeanComponent;
import com.bartolay.inventory.datatable.BrandDatatable;
import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.repositories.DataTableRepository;

@Repository
public class BrandDataTableRepositoryImpl extends BeanComponent implements DataTableRepository<BrandDatatable> {

	@Override
	public List<BrandDatatable> findAll(DatatableParameter parameter) {
		// TODO Auto-generated method stub
		System.err.println(parameter);
		String accountInfo = "";
		if(parameter.getFilterForm().getAccountInfo() != 0) {
			accountInfo = " account_info_id =:account_info_id ";
		}
		final String SQL = "SELECT f FROM LeaveApplication f WHERE "+accountInfo+" ORDER BY "+parameter.getOrder()+" "+parameter.getType()+" ";
		Query query = em.createQuery(SQL);
		query.setFirstResult(parameter.getStart());
		query.setMaxResults(parameter.getLength());
		if(parameter.getFilterForm().getAccountInfo() != 0) {
			query.setParameter("account_info_id", parameter.getFilterForm().getAccountInfo());
		}
		return query.getResultList();
	}


}
