package com.bartolay.inventory.repositories.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bartolay.inventory.components.RepositoryComponent;
import com.bartolay.inventory.datatable.model.DatatableColumn;
import com.bartolay.inventory.datatable.model.DatatableParameter;
import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.repositories.BrandDataTableRepository;

@Repository
public class BrandDataTableRepositoryImpl extends RepositoryComponent implements BrandDataTableRepository<Brand> {
	
	@Override
	public long findAllCount(DatatableParameter datatableParameter) {
		String SQL = "SELECT COUNT(b.id) FROM Brand b";
		
		if(datatableParameter.getSearch() != null) {
			SQL += " WHERE b.name like :search";
		}
			
		Query query = em.createQuery( SQL);
		if(datatableParameter.getSearch() != null) {
			query.setParameter("search", "%" + datatableParameter.getSearch() + "%");
		}
		
		return (long) query.getSingleResult();
	}

	@Override
	public List<Brand> findAllData(DatatableParameter datatableParameter) {

		DatatableColumn sortColumn = datatableParameter.getSortColumn();
		String SQL = "SELECT b FROM Brand b";

		if(datatableParameter.getSearch() != null) {
			SQL += " WHERE b.name like :search";
		}
		
		// sort order by column
		if(sortColumn != null && datatableParameter.getSortOrder() != null) {
			System.err.println("sortColumn " + sortColumn);
			SQL += " ORDER BY b."+sortColumn.getData()+ " " + datatableParameter.getSortOrder().name();	
		}

		System.err.println(datatableParameter);
		System.err.println(SQL);

		Query query = em.createQuery(SQL, Brand.class);
		query.setFirstResult(datatableParameter.getStart());
		query.setMaxResults(datatableParameter.getLength());
		
		if(datatableParameter.getSearch() != null) {
			query.setParameter("search", datatableParameter.getSearch() + "%");
		}
		
		return query.getResultList();
	}
}
