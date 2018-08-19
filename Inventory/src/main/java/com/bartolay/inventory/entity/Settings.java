package com.bartolay.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Settings {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "key", unique = true, nullable = false)
	public String key;
	
	@Column(name="name", unique=true, nullable=false)
	public String name;
	
	public String description;
	@Column(name="value", nullable=false)
	public String value;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Settings [key=" + key + ", name=" + name + ", description=" + description + ", value=" + value + "]";
	}

}
