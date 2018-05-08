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
import javax.persistence.Table;

@Entity
@Table(name="item_units")
public class ItemUnits {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_units_generator")
	@SequenceGenerator(name="item_units_generator", sequenceName = "ITEM_UNITS_SER_SEQ")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", nullable=false)
	private Unit unit;
	
	@Column(name="rate", nullable=false)
	private Double rate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "ItemUnits [id=" + id + ", unit=" + unit + ", rate=" + rate + "]";
	}
}
