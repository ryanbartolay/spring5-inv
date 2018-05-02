package com.bartolay.inventory.repositories.impl;

import java.util.Date;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bartolay.inventory.components.RepositoryComponent;
import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.pagination.DataTableResults;
import com.bartolay.inventory.repositories.BrandDataTableRepository;

@Repository
public class BrandDataTableRepositoryImpl extends RepositoryComponent implements BrandDataTableRepository<Brand> {

//	@Override
//	public List<Object> findAll(DatatableRequest request) {
		
		
//		public List<Attendance> findAttendance(Parameter parameter) {
//			String accountInfo = "";
//			if(parameter.getFilterForm().getAccountInfo() != 0) {
//				accountInfo = " AND account_info_id = :account_info_id ";
//			}
//			String SQL = "SELECT S FROM Attendance S WHERE S.date BETWEEN :start AND :end "+accountInfo+" ORDER BY "+parameter.getOrder()+" "+parameter.getType()+" ";
//
//			Query query = em.createQuery(SQL);
//			query.setFirstResult(parameter.getStart());
//			query.setMaxResults(parameter.getLength());
//			query.setParameter("start", new Date(parameter.getFilterForm().getStart().getTime()));
//			query.setParameter("end", new Date(parameter.getFilterForm().getEnd().getTime()));
//			if(parameter.getFilterForm().getAccountInfo() != 0) {
//				query.setParameter("account_info_id", parameter.getFilterForm().getAccountInfo());
//			}
//			return query.getResultList();
		
		// TODO Auto-generated method stub
//		DatatableParameter parameter = new Data
//		System.err.println(parameter);
		
		String accountInfo = "";
//		if(parameter.getFilterForm().getAccountInfo() != 0) {
//			accountInfo = " account_info_id =:account_info_id ";
//		}
//		final String SQL = "SELECT f FROM LeaveApplication f WHERE "+accountInfo+" ORDER BY "+parameter.getOrder()+" "+parameter.getType()+" ";
//		String SQL = "select * from brand order by " +parameter.getOrder()+" "+parameter.getType()+" ";
		
//		System.err.println(SQL);
//		Query query = em.createQuery(SQL);
//		query.setFirstResult(parameter.getStart());
//		query.setMaxResults(parameter.getLength());
//		if(parameter.getFilterForm().getAccountInfo() != 0) {
//			query.setParameter("account_info_id", parameter.getFilterForm().getAccountInfo());
//		}
//		return query.getResultList();
//		return null;
//	}

	@Override
	public DataTableResults<Brand> findAll(DatatableParameter datatableParameter) {
		
		DataTableResults<Brand> result = new DataTableResults<>();
		
		String SQL = "SELECT S FROM Brand S ORDER BY "+datatableParameter.getOrder()+" "+datatableParameter.getType()+" ";
		
//		if(datatableParameter.getSortColumn() != null) {
//			
//		}

		System.err.println(SQL);
		
		Query query = em.createQuery(SQL, Brand.class);
		query.setFirstResult(datatableParameter.getStart());
		query.setMaxResults(datatableParameter.getLength());
	
		result.setRecordsTotal("select count(*) from brand");
		result.setListOfDataObjects(query.getResultList());
		
		return result;
		
	}
}
