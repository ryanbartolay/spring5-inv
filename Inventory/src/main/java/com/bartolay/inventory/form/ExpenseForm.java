package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;

public class ExpenseForm {
	
	private Integer id;
	@NotNull(message="Description is required")
	private String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "ExpenseForm [id=" + id + ", description=" + description + "]";
	}
	
	

}
