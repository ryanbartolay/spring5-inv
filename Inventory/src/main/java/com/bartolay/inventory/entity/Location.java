package com.bartolay.inventory.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Location {
	
	public static final String TABLE_NAME = "location";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_generator")
	@SequenceGenerator(name="location_generator", sequenceName = "LOCATION_SER_SEQ")
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="abbreviation", length=15, updatable=true, insertable=true)
	private String abbreviation;
	
	@Column(name="telephone", length=50, updatable=true, insertable=true)
	private String telephone;
	
	@Column(name="fax", length=50, updatable=true, insertable=true)
	private String fax;
	
	@Column(name="address", length=200, updatable=true, insertable=true)
	private String address;
	
    @Column(name = "email")
    private String email;
    
    @Column(name = "mobile")
    private String mobile;
    
    @Column(name = "website")
    private String website;
    
    @Column(name = "address_city")
    private String addressCity;
    
    @Column(name = "address_zipcode")
    private int addressZipcode;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_country_id", nullable=false, updatable=true)
    private Country addressCountry;
	
	@Column(name="created_date", nullable=false, updatable=false)
	@CreationTimestamp
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable=false, updatable=false)
	private User createdBy;

	@Column(name="updated_date")
	@UpdateTimestamp
	private Date updatedDated;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable=true, updatable=true)
	private User updatedBy;
	
	@Column(name="enabled", nullable=false)
	private boolean enabled;

	public Location() {
		super();
	}

	public Location(int id) {
		super();
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public int getAddressZipcode() {
		return addressZipcode;
	}

	public void setAddressZipcode(int addressZipcode) {
		this.addressZipcode = addressZipcode;
	}

	public Country getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(Country addressCountry) {
		this.addressCountry = addressCountry;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDated() {
		return updatedDated;
	}

	public void setUpdatedDated(Date updatedDated) {
		this.updatedDated = updatedDated;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
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
		Location other = (Location) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", abbreviation=" + abbreviation + ", telephone=" + telephone
				+ ", fax=" + fax + ", address=" + address + ", email=" + email + ", mobile=" + mobile + ", addressCity="
				+ addressCity + ", addressZipcode=" + addressZipcode + ", addressCountry=" + addressCountry
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedDated=" + updatedDated
				+ ", updatedBy=" + updatedBy + ", enabled=" + enabled + "]";
	}
}
