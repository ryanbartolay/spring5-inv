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

import org.hibernate.annotations.CreationTimestamp;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.User;

@Entity
public class Activity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_generator")
	@SequenceGenerator(name="activity_generator", sequenceName = "ACTIVITY_SER_SEQ")
	public int id;
	
	@Column(name="activity_type", length=25, nullable=false, updatable=false)
	private String activityType;
	
	@Column(name="before", columnDefinition="text", length=512)
	private String before;
		
	@Column(name="before2", columnDefinition="text", length=512)
	private String before2;

	@Column(name="after", columnDefinition="text", length=512)
	private String after;
	
	@Column(name="after2", columnDefinition="text", length=512)
	private String after2;
	
	@Column(name="system_number", nullable=true, updatable=false, length=10)
	private String systemNumber;
	
	@Column(name="document_number", unique=false, updatable=false, length=25)
	private String documentNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=true, updatable=false)
	private Location location;
	
	@Column(name="created_date", nullable=false, updatable=false)
	@CreationTimestamp
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", nullable=false, updatable=false)
	private User createdBy;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getBefore() {
		return before;
	}

	public void setBefore(String before) {
		this.before = before;
	}

	public String getBefore2() {
		return before2;
	}

	public void setBefore2(String before2) {
		this.before2 = before2;
	}

	public String getAfter2() {
		return after2;
	}

	public void setAfter2(String after2) {
		this.after2 = after2;
	}

	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Activity other = (Activity) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", activityType=" + activityType + ", before=" + before + ", before2=" + before2
				+ ", after=" + after + ", after2=" + after2 + ", systemNumber=" + systemNumber + ", documentNumber="
				+ documentNumber + ", location=" + location + ", createdDate=" + createdDate + ", createdBy="
				+ createdBy + "]";
	}
	
}
