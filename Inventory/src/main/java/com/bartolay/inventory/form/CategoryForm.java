package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryForm {

	private Integer id;
    @NotNull
    @Size(min=2, max=30, message="Category type length minimum 2 and 30" )
    private String name;
//    @NotNull
//    @Size(min=2, max=120, message="Category description length minimum 2 and 120" )
    private String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "CategoryForm [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
}
