package com.bartolay.inventory.stock.entity;

import java.math.BigDecimal;
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
import org.hibernate.annotations.UpdateTimestamp;

import com.bartolay.inventory.entity.Expense;
import com.bartolay.inventory.entity.User;

@Entity
public class StockReceiveExpense {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_receive_expense_generator")
	@SequenceGenerator(name="stock_receive_expense_generator", sequenceName = "STOCK_RECEIVE_EXPENSE_SER_SEQ")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable=false, updatable=false)
	private StockReceive stockReceive;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="expense_id", nullable=false)
	private Expense expense;
	
	@Column(nullable=false)
	private BigDecimal amount;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StockReceive getStockReceive() {
		return stockReceive;
	}

	public void setStockReceive(StockReceive stockReceive) {
		this.stockReceive = stockReceive;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
