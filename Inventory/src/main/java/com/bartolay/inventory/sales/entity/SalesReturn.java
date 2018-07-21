package com.bartolay.inventory.sales.entity;

import java.math.BigDecimal;
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

import com.bartolay.inventory.entity.User;

@Entity
@Table(name="sales_return")
public class SalesReturn {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_return_generator")
	@SequenceGenerator(name="sales_return_generator", sequenceName = "SALES_RETURN_SER_SEQ")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sales_invoice_system_number", nullable=false, updatable=false)
	private SalesInvoice salesInvoice;
	
	@OneToMany(mappedBy = "salesReturn", fetch=FetchType.LAZY)
	private List<SalesReturnItem> salesReturnItems;
	
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
	
	@Column(name="total_price", nullable=false, precision=10, scale=5)
	private BigDecimal totalPrice;
	
	private String remarks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SalesInvoice getSalesInvoice() {
		return salesInvoice;
	}

	public void setSalesInvoice(SalesInvoice salesInvoice) {
		this.salesInvoice = salesInvoice;
	}

	public List<SalesReturnItem> getSalesReturnItems() {
		return salesReturnItems;
	}

	public void setSalesReturnItems(List<SalesReturnItem> salesReturnItems) {
		this.salesReturnItems = salesReturnItems;
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

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "SalesReturn [id=" + id + ", salesInvoice=" + salesInvoice + ", salesReturnItems=" + salesReturnItems
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedDated=" + updatedDated
				+ ", updatedBy=" + updatedBy + "]";
	}
	
}
