package com.bartolay.inventory.sales.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.entity.User;

@Entity
@Table(name="sales_return_item", indexes= {
		@Index(columnList="id", name="sales_return_item_id")
})
public class SalesReturnItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false, updatable=false)
	private SalesReturn salesReturn;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="sales_invoice_item_id", nullable=false, insertable=true, updatable=false)
	private SalesInvoiceItem salesInvoiceItem;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="sales_invoice_item_reason_id", nullable=true, insertable=true, updatable=true)
	private SalesReturnItemReason salesReturnItemReason;
	
	@Column(nullable=false)
	private BigDecimal quantity;
	
	@Column(name="unit_price", nullable=false, precision=10, scale=5)
	private BigDecimal unitPrice;
	
	@Column(name="unit_price_total", nullable=false, precision=10, scale=5)
	private BigDecimal unitPriceTotal;
	
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
	
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getUnitPriceTotal() {
		return unitPriceTotal;
	}

	public void setUnitPriceTotal(BigDecimal unitPriceTotal) {
		this.unitPriceTotal = unitPriceTotal;
	}
	
	public SalesReturnItemReason getSalesReturnItemReason() {
		return salesReturnItemReason;
	}

	public void setSalesReturnItemReason(SalesReturnItemReason salesReturnItemReason) {
		this.salesReturnItemReason = salesReturnItemReason;
	}

	@Override
	public String toString() {
		return "SalesReturnItem [id=" + id + ", salesReturn=" + salesReturn + ", salesInvoiceItem=" + salesInvoiceItem
				+ ", quantity=" + quantity + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", updatedDated=" + updatedDated + ", updatedBy=" + updatedBy + "]";
	}
	
}
