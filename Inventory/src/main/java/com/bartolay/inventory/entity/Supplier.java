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
public class Supplier {

	// Billing Address
	@Column(name = "billing_address")
	private String billingAddress;

	@Column(name = "billing_city")
	private String billingCity;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "biilling_country_id", nullable = true, updatable = true)
	private Country billingCountry;
	@Column(name = "billing_fax")
	private String billingFax;
	@Column(name = "billing_phone")
	private String billingPhone;
	@Column(name = "billing_state")
	private String billingState;
	@Column(name = "billing_zipcode")
	private String billingZipCode;
	@Column(name = "company_name", unique = true, nullable = false, length = 50)
	private String company_name;

	@Column(name = "company_phones")
	private String company_phone;
	@Column(name = "contact_email")
	private String contact_email;

	@Column(name = "contact_mobile")
	private String contact_mobile;
	@Column(name = "contact_phone")
	private String contact_phone;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by_id", nullable = false, updatable = false)
	private User createdBy;
	@Column(name = "created_date", nullable = false, updatable = false)
	@CreationTimestamp
	private Date createdDate;
	@Column(name = "currency")
	private String currency;
	@Column(name = "facebook")
	private String facebook;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_generator")
	@SequenceGenerator(name = "supplier_generator", sequenceName = "SUPPLIER_SER_SEQ")
	private int id;
	@Column(name = "notes")
	private String notes;
	// Shipping Address
	@Column(name = "shipping_address")
	private String shippingAddress;
	@Column(name = "shipping_city")
	private String shippingCity;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "shipping_country_id", nullable = true, updatable = true)
	private Country shippingCountry;
	@Column(name = "shipping_fax")
	private String shippingFax;
	@Column(name = "shipping_phone")
	private String shippingPhone;
	@Column(name = "shipping_state")
	private String shippingState;
	@Column(name = "shipping_zipcode")
	private String shippingZipCode;

	@Column(name = "skype_name")
	private String skype_name;

	@Column(name = "twitter")
	private String twitter;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by_id", nullable = true, updatable = true)
	private User updatedBy;

	@Column(name = "updated_date")
	@UpdateTimestamp
	private Date updatedDated;

	@Column(name = "website", length = 50)
	private String website;

	public Supplier() {
		
	}
	public Supplier(Integer id) {
		this.id = id;
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

	public String getCompany_phone() {
		return company_phone;
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

	public User getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getCurrency() {
		return currency;
	}

	public String getFacebook() {
		return facebook;
	}

	public int getId() {
		return id;
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

	public User getUpdatedBy() {
		return updatedBy;
	}

	public Date getUpdatedDated() {
		return updatedDated;
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

	public void setCompany_phone(String company_phone) {
		this.company_phone = company_phone;
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

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setUpdatedDated(Date updatedDated) {
		this.updatedDated = updatedDated;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public String toString() {
		return "Supplier [billingAddress=" + billingAddress + ", billingCity=" + billingCity + ", billingCountry="
				+ billingCountry + ", billingFax=" + billingFax + ", billingPhone=" + billingPhone + ", billingState="
				+ billingState + ", billingZipCode=" + billingZipCode + ", company_name=" + company_name
				+ ", company_phone=" + company_phone + ", contact_email=" + contact_email + ", contact_mobile="
				+ contact_mobile + ", contact_phone=" + contact_phone + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", currency=" + currency + ", facebook=" + facebook + ", id=" + id + ", notes=" + notes
				+ ", shippingAddress=" + shippingAddress + ", shippingCity=" + shippingCity + ", shippingCountry="
				+ shippingCountry + ", shippingFax=" + shippingFax + ", shippingPhone=" + shippingPhone
				+ ", shippingState=" + shippingState + ", shippingZipCode=" + shippingZipCode + ", skype_name="
				+ skype_name + ", twitter=" + twitter + ", updatedBy=" + updatedBy + ", updatedDated=" + updatedDated
				+ ", website=" + website + "]";
	}

}
