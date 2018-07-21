package com.bartolay.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "color")
public class Color {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "color_generator")
	@SequenceGenerator(name="color_generator", sequenceName = "color_seq")
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(name="name", nullable=false, unique=true)
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Color [id=" + id + ", name=" + name + "]";
	}
}
