package com.bartolay.inventory.datatable.model;

import java.util.Date;

public class DatatableFilterForm {
	private String dateRange;
	private Date start;
	private Date end;
	private Date date;
	private long accountInfo;
	
	public String getDateRange() {
		return dateRange;
	}
	public void setDateRange(String dateRange) {
		this.dateRange = dateRange;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getAccountInfo() {
		return accountInfo;
	}
	public void setAccountInfo(long accountInfo) {
		this.accountInfo = accountInfo;
	}
	@Override
	public String toString() {
		return "DatatableFilterForm [dateRange=" + dateRange + ", start=" + start + ", end=" + end + ", date=" + date
				+ ", accountInfo=" + accountInfo + "]";
	}
}
