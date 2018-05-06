package com.bartolay.inventory.itemForm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemForm {
	
	private Long id;
	
	@NotNull
    @Size(min=2, max=10, message="Code length minimum 2 and 10" )
	private String code;
	
	@NotNull
    @Size(min=2, max=10, message="Code length minimum 2 and 150" )
	private String name;
	
	@NotNull
	private Long brandId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
}