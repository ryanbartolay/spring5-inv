package com.bartolay.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Model {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_generator")
	@SequenceGenerator(name="model_generator", sequenceName = "MOD_SER_SEQ")
	@Column(name = "id", updatable = false, nullable = false)
	private Integer serial;
	private String description;
	public Integer getSerial() {
		return serial;
	}
	public void setSerial(Integer serial) {
		this.serial = serial;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
