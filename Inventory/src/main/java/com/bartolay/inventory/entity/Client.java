package com.bartolay.inventory.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.enums.AccountType;
import com.bartolay.inventory.enums.ClientType;

public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
	@SequenceGenerator(name="client_generator", sequenceName = "CLIENT_SER_SEQ")
    private int id;
    @Column(name = "name", nullable=false, length=250)
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "address_city")
    private String addressCity;
    @Column(name = "address_zipcode")
    private int addressZipcode;
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_country_id", nullable=false, updatable=true)
    private Country addressCountry;
    
    @Column(name = "client_type", nullable=false, insertable = true)
    private String clientType; 
    
    @Column(name="created_date", nullable=false, updatable=false)
	@CreationTimestamp
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable=true, updatable=false)
	private User createdBy;

	@Column(name="updated_date")
	@UpdateTimestamp
	private Date updatedDated;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by", nullable=true, updatable=true)
	private User updatedBy;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
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
}
