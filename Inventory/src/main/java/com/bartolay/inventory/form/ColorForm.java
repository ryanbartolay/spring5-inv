package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ColorForm {

	private Long id;
	
	@NotNull
    @Size(min=2, max=30, message="Color name length minimum 2 and 30" )
    private String name;

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

	@Override
	public String toString() {
		return "ColorForm [id=" + id + ", name=" + name + "]";
	}

	
	
}
