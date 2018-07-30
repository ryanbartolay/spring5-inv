package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;

import com.bartolay.inventory.enums.StockAdjustmentType;

public class SalesInvoiceCancelForm {
	
	@NotNull(message="System Number is required")
	private String systemNumber;
	
	@NotNull (message="Required adjustment type")
	private StockAdjustmentType adjustmentType;

	public String getSystemNumber() {
		return systemNumber;
	}

	public void setSystemNumber(String systemNumber) {
		this.systemNumber = systemNumber;
	}

	public StockAdjustmentType getAdjustmentType() {
		return adjustmentType;
	}

	public void setAdjustmentType(StockAdjustmentType adjustmentType) {
		this.adjustmentType = adjustmentType;
	}

	@Override
	public String toString() {
		return "SalesInvoiceCancelForm [systemNumber=" + systemNumber + ", adjustmentType=" + adjustmentType + "]";
	}
}
