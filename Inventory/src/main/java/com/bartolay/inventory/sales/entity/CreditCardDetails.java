package com.bartolay.inventory.sales.entity;

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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.entity.User;

@Entity
@Table(name="credit_card_details")
public class CreditCardDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_card_details_generator")
	@SequenceGenerator(name="credit_card_details_generator", sequenceName = "CREDIT_CARD_DETAILS_SER_SEQ")
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(name="holders_name", length=50, nullable=false)
	private String holdersName;
	
	@Column(name="card_number", length=25, nullable=false)
	private String cardNumber;
	
	@Column(name="month_expiry", length=2, nullable=false)
	private int monthExpiry;
	
	@Column(name="year_expiry", length=4, nullable=false)
	private int yearExpiry;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHoldersName() {
		return holdersName;
	}

	public void setHoldersName(String holdersName) {
		this.holdersName = holdersName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getMonthExpiry() {
		return monthExpiry;
	}

	public void setMonthExpiry(int monthExpiry) {
		this.monthExpiry = monthExpiry;
	}

	public int getYearExpiry() {
		return yearExpiry;
	}

	public void setYearExpiry(int yearExpiry) {
		this.yearExpiry = yearExpiry;
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
