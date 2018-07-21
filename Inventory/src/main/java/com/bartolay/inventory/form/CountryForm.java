package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryForm {
	
	private Integer id;
	
	@NotNull
    @Size(min=3, max=3, message="Country abbreviation length must be 3 characters" )
    private String abbreviation;
	
	@NotNull
    @Size(min=2, max=30, message="Country name length minimum 2 and 30" )
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
		return "CountryForm [id=" + id + ", abbreviation=" + abbreviation + ", name=" + name + "]";
	}

	
}
