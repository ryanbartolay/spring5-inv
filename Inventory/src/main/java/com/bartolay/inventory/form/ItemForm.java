package com.bartolay.inventory.form;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bartolay.inventory.entity.Brand;
import com.bartolay.inventory.entity.Category;
import com.bartolay.inventory.entity.Color;
import com.bartolay.inventory.entity.Country;
import com.bartolay.inventory.entity.ItemUnit;
import com.bartolay.inventory.entity.Model;
import com.bartolay.inventory.entity.Supplier;
import com.bartolay.inventory.entity.Unit;

public class ItemForm {
	
	private Integer id;
	
	@NotNull
    @Size(min=2, max=10, message="Code length minimum 2 and 10" )
	private String code;
	
	@NotNull
    @Size(min=2, max=150, message="Name length minimum 2 and 150" )
	private String name;
	
	@NotNull(message="Brand is required")
	private Brand brand;
	
	@NotNull(message="Category is required")
	private Category category;
	
	@NotNull(message="Color is required")
	private Color color;
	
	@NotNull(message="Model is required")
	private Model model;
	
	@NotNull(message="Size is required")
	private com.bartolay.inventory.entity.Size itemSize;
	
	@NotNull(message="Supplier is required")
	private Supplier supplier;
	
	@NotNull(message="Country is required")
	private Country country;
	
	@NotNull(message="Unit is required")
	private Unit unit;
	
	private Set<ItemUnit> itemUnits = new HashSet<>();
	
	public ItemForm() {
//		itemUnits.add(new ItemUnit());
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
	
	public Set<ItemUnit> getItemUnits() {
		return itemUnits;
	}

	public void setItemUnits(Set<ItemUnit> itemUnits) {
		this.itemUnits = itemUnits;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public com.bartolay.inventory.entity.Size getItemSize() {
		return itemSize;
	}

	public void setItemSize(com.bartolay.inventory.entity.Size itemSize) {
		this.itemSize = itemSize;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
}