package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BrandForm {
	@NotNull
    @Size(min=2, max=30, message="Name length minimum 2 and 30" )
    private String name;

    @NotNull
    private Long company_id;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
}
