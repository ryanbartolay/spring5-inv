package com.bartolay.inventory.repositories.impl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.repositories.JdbcRepository;

@Repository
public class BrandJdbcRepository extends JdbcRepository  {
	
	@Autowired
	private EntityManager entityManager;
	
//	public DataTableResults<BrandDatatable> paginatedFindAll(DatatableRequest dataTableInRQ) {
//		
//		PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
//		
//		String sql = "select b.id, b.name, c.name as company_name from brand b inner join company c on b.company_id = c.id";
//		String sql_count = "select count(*) from brand b inner join company c on b.company_id = c.id";
//		
//		String paginatedQuery = PaginationUtils.buildPaginatedQuery(sql, pagination);
//		
//		System.err.println(paginatedQuery);
//		
//		DataTableResults<BrandDatatable> dataTableResult = new DataTableResults<BrandDatatable>();
//		dataTableResult.setDraw(dataTableInRQ.getDraw());
//		
////		dataTableResult.setRecordsTotal(recordsTotal);
//		
//Query query = entityManager.createNativeQuery(paginatedQuery, BrandDatatable.class);
//		
//		@SuppressWarnings("unchecked")
//		List<BrandDatatable> brandList = query.getResultList();
//		
//		
////		List<BrandDatatable> brandList = jdbcTemplate.query(paginatedQuery, new Object[] {}, new RowMapper<BrandDatatable>() {
////
////			@Override
////			public BrandDatatable mapRow(ResultSet rs, int arg1) throws SQLException {
////				BrandDatatable brand = new BrandDatatable();
////				brand.setName(rs.getString("name"));
////				brand.setCompany_name(rs.getString("company_name"));
////				return brand;
////			}
////		});
//		
//		dataTableResult.setListOfDataObjects(brandList);
//		
//		Long count = this.getSQLCount(sql_count);
//		
//		
////		if (!AppUtil.isObjectEmpty(brandList)) {
////			dataTableResult.setRecordsTotal(brandList.get(0).getTotalRecords()
////					.toString());
////			if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
////				dataTableResult.setRecordsFiltered(brandList.get(0).getTotalRecords()
////						.toString());
////			} else {
////				dataTableResult.setRecordsFiltered(Integer.toString(userList.size()));
////			}
////		}
//		
//		dataTableResult.setRecordsTotal(count.toString());
//		dataTableResult.setRecordsFiltered(count.toString());
//		
//		 return dataTableResult;
////		return jdbcTemplate.query(sql, new ResultSetExtractor<JSONObject>() {
//
//	}
//	
//	public Long getSQLCount(String sql) {
//		return jdbcTemplate.queryForObject(sql, new RowMapper<Long>() {
//
//			@Override
//			public Long mapRow(ResultSet rs, int arg1) throws SQLException {
//				return rs.getLong(1);
//			}
//			
//		});
//	}

}
