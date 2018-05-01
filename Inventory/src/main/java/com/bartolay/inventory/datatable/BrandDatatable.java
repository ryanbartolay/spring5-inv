package com.bartolay.inventory.datatable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BrandDatatable {
	
	@Id
	private Long id;
	
	private String name;
	private String company_name;
	
	private Integer totalRecords;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public Integer getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}
}
