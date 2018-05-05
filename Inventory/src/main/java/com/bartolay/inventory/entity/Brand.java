package com.bartolay.inventory.entity;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Brand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_generator")
	@SequenceGenerator(name="brand_generator", sequenceName = "BRAND_SER_SEQ")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@OneToMany(mappedBy = "brand")
	private List<Model> models = new ArrayList<>();

	@Column(nullable=true, length=50)
	private String nameArabic;

	@Column(nullable=false, length=50)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable=false)
	private Company company;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable=false, updatable=false)
	private User createdBy;

	@Column(name="created_date", nullable=false, updatable=false)
	@CreationTimestamp
	private Date createdDate;

	@Column(name="updated_date")
	@UpdateTimestamp
	private Date updatedDated;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Model> getModels() {
		return models;
	}
	public void setModels(List<Model> models) {
		this.models = models;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public String getNameArabic() {
		return nameArabic;
	}
	public void setNameArabic(String nameArabic) {
		
		for(Byte b : nameArabic.getBytes(StandardCharsets.UTF_8)) {
			System.err.println(b);
		}
		
		this.nameArabic = new String(nameArabic.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Override
	public String toString() {
		return "Brand [id=" + id + ", models=" + models + ", nameArabic=" + nameArabic + ", name=" + name 
				+ ", company=" + company + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "]";
	}
}
