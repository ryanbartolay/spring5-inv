package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BrandForm {
	@NotNull
    @Size(min=2, max=30)
    private String name;

    @NotNull
    private Integer company_id;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
}
