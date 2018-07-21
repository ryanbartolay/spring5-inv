package com.bartolay.inventory.datatable.model;

public class DatatableColumn {
	
	private int index;
	private String data;
	private String name;
	private boolean searchable;
	private boolean orderable;
	private String search;
	private boolean regex;
	
	public DatatableColumn() {
		
	}
	public DatatableColumn(int index) {
		this.index = index;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSearchable() {
		return searchable;
	}
	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}
	public boolean isOrderable() {
		return orderable;
	}
	public void setOrderable(boolean orderable) {
		this.orderable = orderable;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public boolean isRegex() {
		return regex;
	}
	public void setRegex(boolean regex) {
		this.regex = regex;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatatableColumn other = (DatatableColumn) obj;
		if (index != other.index)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DatatableColumn [index=" + index + ", data=" + data + ", name=" + name + ", searchable="
				+ searchable + ", orderable=" + orderable + ", search=" + search + ", regex=" + regex + "]";
	}
}
