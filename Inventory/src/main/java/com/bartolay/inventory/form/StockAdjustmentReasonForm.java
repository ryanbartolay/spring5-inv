package com.bartolay.inventory.form;

import javax.validation.constraints.NotNull;

public class StockAdjustmentReasonForm {
	@NotNull(message="You must specify a description")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
