package com.bartolay.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Model {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_generator")
	@SequenceGenerator(name="model_generator", sequenceName = "MOD_SER_SEQ")
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable=false)
    private Brand brand;
	
	private String description;
	
	public Model() {
	}
	public Model(int id) {
		this.id=id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
}
