package com.bartolay.inventory.itemForm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.entity.Color;

public class ItemForm {
	
	private Long id;
	
	@NotNull
    @Size(min=2, max=10, message="Code length minimum 2 and 10" )
	private String code;
	
	@NotNull
    @Size(min=2, max=150, message="Name length minimum 2 and 150" )
	private String name;
	
	@NotNull
	private Brand brand;
	
	@NotNull
	private Color color;

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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}