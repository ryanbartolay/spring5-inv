package com.bartolay.inventory.sales.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.entity.User;

@Entity
@Table(name="sales_return_item")
public class SalesReturnItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_return_item_generator")
	@SequenceGenerator(name="sales_return_item_generator", sequenceName = "SALES_RETURN_ITEM_SER_SEQ")
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false, updatable=false)
	private SalesReturn salesReturn;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="sales_invoice_item_id", nullable=false, insertable=true, updatable=false)
	private SalesInvoiceItem salesInvoiceItem;
	
	@Column(nullable=false)
	private BigDecimal quantity;
	
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

	public SalesReturn getSalesReturn() {
		return salesReturn;
	}

	public void setSalesReturn(SalesReturn salesReturn) {
		this.salesReturn = salesReturn;
	}

	public SalesInvoiceItem getSalesInvoiceItem() {
		return salesInvoiceItem;
	}

	public void setSalesInvoiceItem(SalesInvoiceItem salesInvoiceItem) {
		this.salesInvoiceItem = salesInvoiceItem;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
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

	@Override
	public String toString() {
		return "SalesReturnItem [id=" + id + ", salesReturn=" + salesReturn + ", salesInvoiceItem=" + salesInvoiceItem
				+ ", quantity=" + quantity + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", updatedDated=" + updatedDated + ", updatedBy=" + updatedBy + "]";
	}
	
}
