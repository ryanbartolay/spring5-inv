package com.bartolay.inventory.datatable.model;

import java.util.Set;

import com.bartolay.inventory.datatable.SortOrder;

public class DatatableParameter {
	private int draw;
	private int start;
	private int length;
	
	private String search;
	private boolean regex;
	private String order;
	private String type;
	private String uniqueId;
	
	private int sortColumn;
	private SortOrder sortOrder;
	
	private Set<DatatableColumn> columns;
	
//	private String []data;
//	private String []searchable;
//	private String []orderable;
//	private String []name;
//	private String []orderColumns;
//	private String []orderDir;
//	
//	private DatatableFilterForm filterForm;
	
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
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public boolean getRegex() {
		return regex;
	}
	public void setRegex(boolean regex) {
		this.regex = regex;
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
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public int getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(int sortColumn) {
		this.sortColumn = sortColumn;
	}
	public SortOrder getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Set<DatatableColumn> getColumns() {
		return columns;
	}
	public void setColumns(Set<DatatableColumn> columns) {
		this.columns = columns;
	}
	@Override
	public String toString() {
		return "DatatableParameter [draw=" + draw + ", start=" + start + ", length=" + length + ", search=" + search
				+ ", regex=" + regex + ", order=" + order + ", type=" + type + ", uniqueId=" + uniqueId
				+ ", sortColumn=" + sortColumn + ", sortOrder=" + sortOrder + ", columns=" + columns + "]";
	}
}
