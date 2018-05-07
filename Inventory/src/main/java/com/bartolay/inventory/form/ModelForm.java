package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bartolay.inventory.entity.Brand;

public class ModelForm {

	private Long id;
	@NotNull
	private Brand brand;
	@NotNull
    @Size(min=2, max=120, message="Model description length minimum 2 and 120" )
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
	@Override
	public String toString() {
		return "ModelForm [id=" + id + ", brand=" + brand + ", description=" + description + "]";
	}
}
