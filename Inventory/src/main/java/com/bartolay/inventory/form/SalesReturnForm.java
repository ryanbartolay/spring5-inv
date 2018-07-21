package com.bartolay.inventory.form;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.bartolay.inventory.sales.entity.SalesInvoice;
import com.bartolay.inventory.sales.entity.SalesReturnItem;

public class SalesReturnForm {
	
	@NotNull(message="You must specify sales invoice")
	private SalesInvoice salesInvoice;
	
	@NotNull(message="Needed atleast 1 item")
	private List<SalesReturnItem> salesReturnItems;
	
	private String remarks;

	public SalesInvoice getSalesInvoice() {
		return salesInvoice;
	}

	public void setSalesInvoice(SalesInvoice salesInvoice) {
		this.salesInvoice = salesInvoice;
	}

	public List<SalesReturnItem> getSalesReturnItems() {
		return salesReturnItems;
	}

	public void setSalesReturnItems(List<SalesReturnItem> salesReturnItems) {
		this.salesReturnItems = salesReturnItems;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "SalesReturnForm [salesInvoice=" + salesInvoice + ", salesReturnItems=" + salesReturnItems + ", remarks="
				+ remarks + "]";
	}
		
}
