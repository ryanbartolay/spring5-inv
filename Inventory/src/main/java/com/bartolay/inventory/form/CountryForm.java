package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryForm {
	
	private int id;
	
	@NotNull
    @Size(min=3, max=3, message="Country code length must be 3 characters" )
    private String code;
	
	@NotNull
    @Size(min=2, max=30, message="Country name length minimum 2 and 30" )
    private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CountryForm [id=" + id + ", code=" + code + ", name=" + name + "]";
	}
}
