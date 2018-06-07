package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ColorForm {

	private int id;
	
	@NotNull
    @Size(min=2, max=30, message="Color name length minimum 2 and 30" )
    private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
