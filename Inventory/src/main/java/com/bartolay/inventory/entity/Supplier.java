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

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_generator")
	@SequenceGenerator(name="supplier_generator", sequenceName = "SUPPLIER_SER_SEQ")
    private int id;
	
    @Column(name = "name", unique=true, nullable=false, length=50)
    private String name;
    
    @Column(name = "phone", length=20)
    private String phone;
    
    @Column(name = "mobile", length=20)
    private String mobile;

    @Column(name = "fax", length=20)
    private String fax;
    
    @Column(name = "website", length=50)
    private String website;
    
    @Column(name = "address")
    private String address;
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
	@JoinColumn(name = "created_by_id", nullable=false, updatable=false)
	private User createdBy;
	
	@Column(name="updated_date")
	@UpdateTimestamp
	private Date updatedDated;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by_id", nullable=true, updatable=true)
	private User updatedBy;

    public Supplier() {
    }

    public Supplier(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Supplier(int id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
	
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", phone=" + phone + ", mobile=" + mobile + ", address="
				+ address + ", addressCity=" + addressCity + ", addressZipcode=" + addressZipcode + ", addressCountry="
				+ addressCountry + ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedDated="
				+ updatedDated + ", updatedBy=" + updatedBy + "]";
	}
}
