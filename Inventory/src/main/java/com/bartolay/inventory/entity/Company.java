package com.bartolay.inventory.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_generator")
	@SequenceGenerator(name="company_generator", sequenceName = "COMPANY_SER_SEQ")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(unique=true, length=100)
	private String nameArabic;
	
	@Column(unique=true, nullable=false, length=100)
	private String name;
	
	@Column(name="contact_name")
	private String contactName;
	
	@Column(name="contact_position")
	private String contactPosition;
	
	@Column(name="telephone1")
	private String telephone1;
	
	@Column(name="telephone2")
	private String telephone2;
	
	@Column(name="telephone3")
	private String telephone3;
	
	private String fax;
	
	private String mobile;
	
	private String pager;
	
	private String addressArabic;
	
	private String address;
	
	private String title;
	
	@Column(name="last", length=3)
	private String last;
	
	private String logo;
	
	private String pobox;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@Column(name="site")
	private String site;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNameArabic() {
		return nameArabic;
	}

	public void setNameArabic(String nameArabic) {
		this.nameArabic = nameArabic;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPosition() {
		return contactPosition;
	}

	public void setContactPosition(String contactPosition) {
		this.contactPosition = contactPosition;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getTelephone3() {
		return telephone3;
	}

	public void setTelephone3(String telephone3) {
		this.telephone3 = telephone3;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPager() {
		return pager;
	}

	public void setPager(String pager) {
		this.pager = pager;
	}

	public String getAddressArabic() {
		return addressArabic;
	}

	public void setAddressArabic(String addressArabic) {
		this.addressArabic = addressArabic;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPobox() {
		return pobox;
	}

	public void setPobox(String pobox) {
		this.pobox = pobox;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", nameArabic=" + nameArabic + ", name=" + name + ", contactName=" + contactName
				+ ", contactPosition=" + contactPosition + ", telephone1=" + telephone1 + ", telephone2=" + telephone2
				+ ", telephone3=" + telephone3 + ", fax=" + fax + ", mobile=" + mobile + ", pager=" + pager
				+ ", addressArabic=" + addressArabic + ", address=" + address + ", title=" + title + ", last=" + last
				+ ", logo=" + logo + ", pobox=" + pobox + ", email=" + email + ", site=" + site + "]";
	}
}
