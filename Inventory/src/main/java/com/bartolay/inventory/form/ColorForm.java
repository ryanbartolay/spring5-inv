package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ColorForm {

	private Long id;
	
	@NotNull
    @Size(min=2, max=30, message="Color length minimum 2 and 30" )
    private String color;
	@NotNull
    @Size(min=2, max=10, message="Color length minimum 2 and 30" )
    private String code;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ColorForm [id=" + id + ", color=" + color + ", code=" + code + "]";
	}
	
}
