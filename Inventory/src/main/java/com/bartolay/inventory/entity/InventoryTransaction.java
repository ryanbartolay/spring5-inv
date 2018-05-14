package com.bartolay.inventory.entity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.bartolay.inventory.enums.TransactionType;

@Deprecated
public class InventoryTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_transaction_generator")
	@SequenceGenerator(name="inventory_transaction_generator", sequenceName = "INVENTORY_TRANSACTION_SER_SEQ")
	public int id;
	
	@Column(name="transaction_type_id", nullable=false, length=3)
	private TransactionType transactionType;
	
	@Column(name="transaction_system_number", nullable=false)
	private String transactionSystemNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="location_id", nullable=false, updatable=false)
	private Location location;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="item_id", nullable=false, updatable=false)
	public Item item;
}
