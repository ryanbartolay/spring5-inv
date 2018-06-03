package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bartolay.inventory.entity.Country;

public class LocationForm {

	private Integer id;
	
	@NotNull
    @Size(min=3, max=30, message="Location name length must be 30 characters" )
	private String name;
	
	@NotNull
    @Size(min=3, max=15, message="Location abbreviation length must be 15 characters" )
	private String abbreviation;
	
	@NotNull
    @Size(min=3, max=50, message="Location telephone length must be 50 characters" )
	private String telephone;
	
	@NotNull
    @Size(min=3, max=50, message="Location fax length must be 50 characters" )
	private String fax;
	
	@NotNull
    @Size(min=3, max=200, message="Location address length must be 200 characters" )
	private String address;

	@NotNull
	private String addressCity;
	
	@NotNull
	private Country addressCountry;

	private String mobile;
	private String website;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	
	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public Country getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(Country addressCountry) {
		this.addressCountry = addressCountry;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}	
}
