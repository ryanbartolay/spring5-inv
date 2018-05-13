package com.bartolay.inventory.entity;

import java.math.BigDecimal;

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
public class ItemUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_units_generator")
	@SequenceGenerator(name="item_units_generator", sequenceName = "ITEM_UNITS_SER_SEQ")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id", nullable=false, updatable=true)
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", nullable=false)
	private Unit unit;
	
	/**
	 * int - precision - (Optional) The precision for a decimal (exact numeric) column. (Applies only if a decimal column is used.)
	 * int - scale - (Optional) The scale for a decimal (exact numeric) column. (Applies only if a decimal column is used.)
	 */
	@Column(name="rate", nullable=false, precision=10, scale=5)
	private BigDecimal rate;
	
	public ItemUnit() {
	}
	
	public ItemUnit(Long id) {
		this.id=id;
	}
	
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
		ItemUnit other = (ItemUnit) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemUnits [id=" + id + ", unit=" + unit + ", rate=" + rate + "]";
	}
}
