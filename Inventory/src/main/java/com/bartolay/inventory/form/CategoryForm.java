package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryForm {

	private Long id;
    @NotNull
    @Size(min=2, max=30, message="Category type length minimum 2 and 30" )
    private String type;
    @NotNull
    @Size(min=2, max=120, message="Category description length minimum 2 and 120" )
    private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "CategoryForm [id=" + id + ", type=" + type + ", description=" + description + "]";
	}
}
