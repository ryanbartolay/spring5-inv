package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bartolay.inventory.entity.Country;

public class SupplierForm {

	private Integer supplierId;
	// Billing Address
	private String billingAddress;

	private String billingCity;

	private Country billingCountry;

	private String billingFax;

	private String billingPhone;

	private String billingState;

	private String billingZipCode;

	@NotNull(message = "Company name is required")
	@Size(min = 2, max = 100, message = "Company Name is required. Length must be 2 to 100 characters.")
	private String company_name;

	private String contact_email;
	private String contact_mobile;
	private String contact_phone;
	private String currency;
	private String facebook;
	private String notes;
	// Shipping Address
	private String shippingAddress;
	private String shippingCity;

	private Country shippingCountry;
	private String shippingFax;
	private String shippingPhone;
	private String shippingState;
	private String shippingZipCode;
	private String skype_name;
	private String twitter;

	private String website;

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public String getBillingCity() {
		return billingCity;
	}

	public Country getBillingCountry() {
		return billingCountry;
	}

	public String getBillingFax() {
		return billingFax;
	}

	public String getBillingPhone() {
		return billingPhone;
	}

	public String getBillingState() {
		return billingState;
	}

	public String getBillingZipCode() {
		return billingZipCode;
	}

	public String getCompany_name() {
		return company_name;
	}

	public String getContact_email() {
		return contact_email;
	}

	public String getContact_mobile() {
		return contact_mobile;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public String getCurrency() {
		return currency;
	}

	public String getFacebook() {
		return facebook;
	}

	public String getNotes() {
		return notes;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public Country getShippingCountry() {
		return shippingCountry;
	}

	public String getShippingFax() {
		return shippingFax;
	}

	public String getShippingPhone() {
		return shippingPhone;
	}

	public String getShippingState() {
		return shippingState;
	}

	public String getShippingZipCode() {
		return shippingZipCode;
	}

	public String getSkype_name() {
		return skype_name;
	}

	public String getTwitter() {
		return twitter;
	}

	public String getWebsite() {
		return website;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	public void setBillingCountry(Country billingCountry) {
		this.billingCountry = billingCountry;
	}

	public void setBillingFax(String billingFax) {
		this.billingFax = billingFax;
	}

	public void setBillingPhone(String billingPhone) {
		this.billingPhone = billingPhone;
	}

	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	public void setBillingZipCode(String billingZipCode) {
		this.billingZipCode = billingZipCode;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}

	public void setContact_mobile(String contact_mobile) {
		this.contact_mobile = contact_mobile;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public void setShippingCountry(Country shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	public void setShippingFax(String shippingFax) {
		this.shippingFax = shippingFax;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}

	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}

	public void setShippingZipCode(String shippingZipCode) {
		this.shippingZipCode = shippingZipCode;
	}

	public void setSkype_name(String skype_name) {
		this.skype_name = skype_name;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}
