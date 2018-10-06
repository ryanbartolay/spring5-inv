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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.entity.Inventory;
import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.enums.Status;

@Entity
@Table(name = "sales_invoice_item", indexes = {
		@Index(columnList="systemNumber", name="sales_invoice_systemNumber"),
		@Index(columnList="documentNumber", name="sales_invoice_documentNumber"),
})
public class SalesInvoiceItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false, updatable=false)
	private SalesInvoice salesInvoice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="inventory_id", nullable=false, updatable=false)
	private Inventory inventory;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="item_id", nullable=false, updatable=false)
//	private Item item;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="unit_id", nullable=false, updatable=true)
//	private Unit unit;
	
	@Column(name="quantity", updatable=true)
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
	
	@Column(name="status", nullable=false, updatable=true, length=3)
	private Status status;
	
	public SalesInvoiceItem() {
		super();
	}

	public SalesInvoiceItem(int id) {
		super();
		this.id = id;
	}

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
	
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
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
		SalesInvoiceItem other = (SalesInvoiceItem) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SalesInvoiceItem [id=" + id + ", quantity="
				+ quantity + ", unitCost=" + unitPrice + ", createdDate=" + createdDate
//				+ ", createdBy=" + createdBy 
				+ ", updatedDated=" + updatedDated 
//				+ ", updatedBy=" + updatedBy
				+ ", status=" + status + "]";
	}
	
}
