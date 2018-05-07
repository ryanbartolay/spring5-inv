package com.bartolay.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="country", uniqueConstraints={
	    @UniqueConstraint(columnNames = {"code"})
	})
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_generator")
	@SequenceGenerator(name="country_generator", sequenceName = "COUNTRY_SER_SEQ")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	private String name;
	private String code;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", code=" + code + "]";
	}
}
