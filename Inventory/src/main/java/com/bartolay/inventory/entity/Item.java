package com.bartolay.inventory.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_generator")
	@SequenceGenerator(name="item_generator", sequenceName = "ITEM_SER_SEQ")
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(nullable=false, length=50, unique=true)
	private String code;
	
	@Column(nullable=false, length=150)
    private String name;
	
	@Column(name="minimum_item_price",  nullable=false, precision=10, scale=5)
	private BigDecimal minimumItemPrice;
	
	@Column(name="maximum_item_price",  nullable=true, precision=10, scale=5)
	private BigDecimal maximumItemPrice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable=true)
    private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id", nullable=false)
	private Brand brand;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "color_id", nullable=false)
	private Color color;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "model_id", nullable=true)
	private Model model;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "default_unit_id", nullable=false)
	private Unit defaultUnit;
	
	@OneToMany(mappedBy = "item", fetch=FetchType.LAZY)
	private Set<ItemUnit> itemUnits;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable=false, updatable=false)
	private User createdBy;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable=true, updatable=true)
	private User updatedBy;

	@Column(name="created_date", nullable=false, updatable=false)
	@CreationTimestamp
	private Date createdDate;

	@Column(name="updated_date")
	@UpdateTimestamp
	private Date updatedDated;
	
	@Column(nullable=false)
	private boolean enabled;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "size_id", nullable=true, updatable=true)
	private Size size;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id", nullable=true, updatable=true)
	private Country country;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id", nullable=true, updatable=true)
	private Supplier supplier;
	
	public Item() {
	
	}
	
	public Item(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Unit getDefaultUnit() {
		return defaultUnit;
	}

	public void setDefaultUnit(Unit defaultUnit) {
		this.defaultUnit = defaultUnit;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDated() {
		return updatedDated;
	}

	public void setUpdatedDated(Date updatedDated) {
		this.updatedDated = updatedDated;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<ItemUnit> getItemUnits() {
		return itemUnits;
	}

	public void setItemUnits(Set<ItemUnit> itemUnits) {
		this.itemUnits = itemUnits;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public BigDecimal getMinimumItemPrice() {
		return minimumItemPrice;
	}

	public void setMinimumItemPrice(BigDecimal minimumItemPrice) {
		this.minimumItemPrice = minimumItemPrice;
	}

	public BigDecimal getMaximumItemPrice() {
		return maximumItemPrice;
	}

	public void setMaximumItemPrice(BigDecimal maximumItemPrice) {
		this.maximumItemPrice = maximumItemPrice;
	}

	public boolean isDefaultUnit(Unit unit) {
		return this.defaultUnit.equals(unit);
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
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", code=" + code + ", name=" + name + ", defaultUnit=" + defaultUnit + "]";
	}
}