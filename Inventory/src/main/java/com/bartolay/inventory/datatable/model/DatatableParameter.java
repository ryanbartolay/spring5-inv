package com.bartolay.inventory.datatable.model;

import java.util.Arrays;

public class DatatableParameter {
	private int draw;
	private int start;
	private int length;
	private String []data;
	private String []searchable;
	private String []orderable;
	private String []name;
	private String []search;
	private String []orderColumns;
	private String []orderDir;
	private String order;
	private String type;
	private DatatableFilterForm filterForm;
	
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
	public String[] getSearchable() {
		return searchable;
	}
	public void setSearchable(String[] searchable) {
		this.searchable = searchable;
	}
	public String[] getOrderable() {
		return orderable;
	}
	public void setOrderable(String[] orderable) {
		this.orderable = orderable;
	}
	public String[] getName() {
		return name;
	}
	public void setName(String[] name) {
		this.name = name;
	}
	public String[] getSearch() {
		return search;
	}
	public void setSearch(String[] search) {
		this.search = search;
	}
	public String[] getOrderColumns() {
		return orderColumns;
	}
	public void setOrderColumns(String[] orderColumns) {
		this.orderColumns = orderColumns;
	}
	public String[] getOrderDir() {
		return orderDir;
	}
	public void setOrderDir(String[] orderDir) {
		this.orderDir = orderDir;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Parameter [draw=" + draw + ", start=" + start + ", length=" + length + ", data=" + Arrays.toString(data)
				+ ", searchable=" + Arrays.toString(searchable) + ", orderable=" + Arrays.toString(orderable)
				+ ", name=" + Arrays.toString(name) + ", search=" + Arrays.toString(search) + ", orderColumns="
				+ Arrays.toString(orderColumns) + ", orderDir=" + Arrays.toString(orderDir) + ", order=" + order
				+ ", type=" + type + ", filterForm=" + filterForm + "]";
	}
	public DatatableFilterForm getFilterForm() {
		return filterForm;
	}
	public void setFilterForm(DatatableFilterForm filterForm) {
		this.filterForm = filterForm;
	}
}
