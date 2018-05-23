package com.bartolay.inventory.form;

import com.bartolay.inventory.entity.Item;

public class StockOpeningItemForm {

	private Integer id;
	
	private Item item;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(nullable=false, updatable=true)
//	private StockOpening stockOpening;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="unit_id", nullable=false, updatable=true)
//	private Unit unit;
//	
//	@Column(name="unit_cost", nullable=false, precision=10, scale=5)
//	private BigDecimal unitCost;
//	
//	@Column(name="quantity", nullable=false, precision=10, scale=5)
//	private BigDecimal quantity;
//	
//	@Column(name="rate_quantity", nullable=false, precision=10, scale=5)
//	private BigDecimal rateQuantity;
//	
//	@Column(name="unit_cost_total", nullable=false, precision=10, scale=5)
//	private BigDecimal unitCostTotal;
//	
//	@Column(name="status", nullable=false, updatable=true, length=3)
//	private Status status;

}
