package com.bartolay.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_generator")
	@SequenceGenerator(name="location_generator", sequenceName = "LOCATION_SER_SEQ")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	private String abbreviation;
	private String telephone;
	private String fax;
	private String address;
	
	@Column(name="enabled", nullable=false)
	private boolean enabled;

	public Location() {
		super();
	}

	public Location(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", abbreviation=" + abbreviation + ", telephone=" + telephone
				+ ", fax=" + fax + ", address=" + address + ", enabled=" + enabled + "]";
	}
}
