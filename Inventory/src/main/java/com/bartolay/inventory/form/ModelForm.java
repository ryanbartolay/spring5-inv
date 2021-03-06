package com.bartolay.inventory.form;

import javax.validation.constraints.Size;

import com.bartolay.inventory.entity.Brand;

public class ModelForm {

	private Long id;
//	@NotNull
	@Size(min=2, max=120, message="Model name length minimum 2 and 30" )
	private String name;
	private Brand brand;
//	@NotNull
//    @Size(min=2, max=120, message="Model description length minimum 2 and 120" )
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "ModelForm [id=" + id + ", name=" + name + ", brand=" + brand + ", description=" + description + "]";
	}
}
