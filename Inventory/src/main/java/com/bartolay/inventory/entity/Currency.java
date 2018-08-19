package com.bartolay.inventory.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Currency {

	@Column(name = "code", length = 3, nullable = false, unique = true)
	private String code;

	@Column(name = "decimal_places")
	private int decimalPlaces;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_generator")
	@SequenceGenerator(name = "currency_generator", sequenceName = "CURRENCY_SER_SEQ")
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "rate", nullable = false)
	private BigDecimal rate;

	public String getCode() {
		return code;
	}

	public int getDecimalPlaces() {
		return decimalPlaces;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDecimalPlaces(int decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}
