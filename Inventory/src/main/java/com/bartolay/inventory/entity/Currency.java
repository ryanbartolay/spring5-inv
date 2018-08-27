package com.bartolay.inventory.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Currency {

	@Column(name = "code", length = 3, nullable = false, unique = true)
	private String code;

	@Column(name = "decimal_places")
	private int decimalPlaces;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
//	@Id
//	@GeneratedValue(generator = "currency_id_seq")
//	@GenericGenerator(name = "currency_id_seq", strategy = "com.bartolay.inventory.entity.generators.AutoIncrementIdGenerator")
//	@Column(name = "id", updatable = false, nullable = false, columnDefinition="SERIAL")
//	private Integer id;

	@Column(name = "name", length = 50, nullable = false)
	private String name;

	@Column(name = "rate", nullable = false)
	private BigDecimal rate;

	@Column(name = "symbol", length = 5)
	private String symbol;
	
	@Column(name="base_currency")
	private boolean baseCurrency;

	public String getCode() {
		return code;
	}

	public int getDecimalPlaces() {
		return decimalPlaces;
	}

	public Integer getId() {
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public boolean isBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(boolean baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Currency other = (Currency) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
