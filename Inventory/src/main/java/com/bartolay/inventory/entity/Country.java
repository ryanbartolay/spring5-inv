package com.bartolay.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_generator")
	@SequenceGenerator(name="country_generator", sequenceName = "COUNTRY_SER_SEQ")
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(name="name", nullable=false, unique=true)
	private String name;
	
	@Column(name="abbreviation", nullable=false, unique=true)
	private String abbreviation;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String code) {
		this.abbreviation = code;
	}
	
	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", code=" + abbreviation + "]";
	}
}
