package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UnitForm {
	
	private Integer id;
	
	@NotNull
    @Size(min=2, max=6, message="Name length minimum 2 and 6" )
	private String abbreviation;
	
	@NotNull
    @Size(min=2, max=30, message="Name length minimum 2 and 30" )
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "UnitForm [id=" + id + ", name=" + name + ", abbreviation=" + abbreviation + "]";
	}
	
	
}
