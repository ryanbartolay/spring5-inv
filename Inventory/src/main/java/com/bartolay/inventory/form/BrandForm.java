package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BrandForm {
	
	private Integer id;
	
	@NotNull
    @Size(min=2, max=30, message="Name length minimum 2 and 30" )
    private String name;

//    @NotNull (message="Company is Required")
    private int company_id;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	@Override
	public String toString() {
		return "BrandForm [hiddenId=" + id + ", name=" + name + ", company_id=" + company_id + "]";
	}
	
	
}
