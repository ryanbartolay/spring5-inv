package com.bartolay.inventory.entity;

import java.util.ArrayList;
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
	
	@Column(nullable=false)
	private char flag;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable=false)
	private Company company;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable=false, updatable=false)
	private User createdBy;

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
}
