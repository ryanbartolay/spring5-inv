package com.bartolay.inventory.stock.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.bartolay.inventory.entity.Location;
import com.bartolay.inventory.entity.Supplier;
import com.bartolay.inventory.enums.PaymentMethod;
import com.bartolay.inventory.repositories.GeneratedSystemNumber;
import com.bartolay.inventory.sales.entity.SalesInvoiceItem;

@Entity
public class StockReceive implements GeneratedSystemNumber {

	@Id
	@GeneratedValue(generator = "StockReceive-UniqueIdGenerator")
	@GenericGenerator(name = "StockReceive-UniqueIdGenerator", strategy = "com.bartolay.inventory.entity.generators.SystemNumberGenerator")
	@Column(name="system_number", nullable=false, unique=true, insertable=false, updatable=false, length=10)
	private String systemNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id", nullable=false)
	private Supplier supplier;
	
	@Enumerated(EnumType.STRING)
	@Column(name="payment_method", nullable=false, length=10, updatable=true)
	private PaymentMethod paymentMethod;
	
	@Column(name="document_number", unique=true, length=25)
	private String documentNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=false, updatable=false)
	private Location location;
	
	@Column(name="year", nullable=false, length=4, updatable=false)
	private String year;
	
	@OneToMany(mappedBy = "stockReceive", fetch=FetchType.LAZY)
	private List<StockReceiveItem> stockReceiveItems;

	
	@Override
	public String getYear() {
		return this.year;
	}

	@Override
	public Location getLocation() {
		return this.location;
	}

	@Override
	public String getTableName() {
		return "stock_receive";
	}

}
