package com.bartolay.inventory.stock.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.bartolay.inventory.entity.InventoryTransaction;

@Entity
@Table(name="stock_adjustment")
public class StockAdjustment {
	@Transient
	public static final String TABLE_NAME = "stock_adjustment";
	
	@Transient
	private Set<InventoryTransaction> inventories;

	@Id
	@GeneratedValue(generator = "StockAdjustmentSystemNumberGenerator")
	@GenericGenerator(name = "StockAdjustmentSystemNumberGenerator", strategy = "com.bartolay.inventory.entity.generators.SystemNumberGenerator")
	@Column(name="system_number", nullable=false, unique=true, insertable=false, updatable=false, length=10)
	private String systemNumber;

	@Column(name="document_number", unique=true, length=25)
	private String documentNumber;
	
	@Column(name="transaction_date", nullable=false)
	@Type(type="date")
	private Date transactionDate;

	@Column(name="year", nullable=false, length=4, updatable=false)
	private String year;
	
	private String description;
}
