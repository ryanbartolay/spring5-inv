package com.bartolay.inventory.entity.stock;

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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.User;

@Entity
@Table(name="stock_opening")
public class StockOpening {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_opening_generator")
	@SequenceGenerator(name="stock_opening_generator", sequenceName = "STOCK_OPENING_SER_SEQ")
	private Long id;
	
	@Column(name="system_number", unique=true)
	private String systemNumber;
	
	@Column(name="document_number")
	private String documentNumber;
	
	@Column(name="transaction_date")
	private Date transactionDate;
	
	private String description;
	
	@OneToMany(mappedBy = "stockOpening", fetch=FetchType.LAZY)
	private List<StockOpeningItem> items;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=false, updatable=true)
	private Location location;
	
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
	
	@Column(name="draft", nullable=false)
	private boolean draft;
	
	@Column(name="enabled", nullable=false)
	private boolean enabled;
	
	public StockOpening() {
		super();
	}

	public StockOpening(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSystemNumber() {
		return systemNumber;
	}

	public void setSystemNumber(String systemNumber) {
		this.systemNumber = systemNumber;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

	public boolean isDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "OpeningStock [id=" + id + ", systemNumber=" + systemNumber + ", documentNumber=" + documentNumber
				+ ", transactionDate=" + transactionDate + ", description=" + description + ", items=" + items
				+ ", location=" + location + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", updatedDated=" + updatedDated + ", updatedBy=" + updatedBy + ", draft=" + draft + ", enabled="
				+ enabled + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		StockOpening other = (StockOpening) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
