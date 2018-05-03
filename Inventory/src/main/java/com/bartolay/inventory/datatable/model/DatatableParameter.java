package com.bartolay.inventory.datatable.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatatableParameter {
	
	private int draw;
	private int start;
	private int length;

	private String search;
	private boolean regex;
	private String type;
	private String uniqueId;

	private int sortColumnId;
	private SortOrder sortOrder;

	private List<DatatableColumn> columns;

	//	private String []data;
	//	private String []searchable;
	//	private String []orderable;
	//	private String []name;
	//	private String []orderColumns;
	//	private String []orderDir;
	//	
	//	private DatatableFilterForm filterForm;
	
	public static final String DRAW = "draw";
	public static final String START = "start";
	public static final String LENGTH = "length";

	public static final String UNIQUE_ID = "_";
	public static final String SEARCH = "search[value]";
	public static final String REGEX = "search[regex]";

	public static final String SORT_COLUMN = "order[0][column]";
	public static final String SORT_ORDER = "order[0][dir]";

	public DatatableParameter(Map<String, String> requestMap) {

		this.setDraw(Integer.parseInt(requestMap.get(DRAW).toString()));
		this.setStart(Integer.parseInt(requestMap.get(START).toString()));
		this.setLength(Integer.parseInt(requestMap.get(LENGTH).toString()));

		this.setUniqueId(requestMap.get(UNIQUE_ID).toString());
		
		String search = requestMap.get(SEARCH);
		
		this.setSearch(search.trim().length() > 0 ? search.trim() : null);
		
		this.setRegex(Boolean.valueOf(requestMap.get(REGEX).toString()));

		this.setSortColumnId(Integer.parseInt(requestMap.get(SORT_COLUMN).toString()));
		this.setSortOrder(SortOrder.valueOf(requestMap.get(SORT_ORDER).toUpperCase().trim()));

		int numberOfColumns = getNumberOfColumns(requestMap);
		System.err.println("numberOfColumns " + numberOfColumns);


		List<DatatableColumn> columns = new ArrayList<>();

		for(int i=0; i < numberOfColumns; i++) {
			if(null != requestMap.get("columns["+ i +"][data]") 
					&& !"null".equalsIgnoreCase(requestMap.get("columns["+ i +"][data]"))  
					&& !isObjectEmpty(requestMap.get("columns["+ i +"][data]"))) {

				DatatableColumn column = new DatatableColumn();
				column.setIndex(i);
				column.setData(requestMap.get("columns["+ i +"][data]"));
				column.setName(requestMap.get("columns["+ i +"][name]"));
				column.setOrderable(Boolean.valueOf(requestMap.get("columns["+ i +"][orderable]")));
				column.setRegex(Boolean.valueOf(requestMap.get("columns["+ i +"][search][regex]")));
				column.setSearch(requestMap.get("columns["+ i +"][search][value]"));
				column.setSearchable(Boolean.valueOf(requestMap.get("columns["+ i +"][searchable]")));

				columns.add(column);
			} 
		}

		this.setColumns(columns);
	}
	
	/**
	 * Return the sort column
	 * @return
	 */
	public DatatableColumn getSortColumn() {
		DatatableColumn sortColumn = new DatatableColumn(this.getSortColumnId());
		if(this.getColumns().contains(sortColumn)) {
			return this.getColumns().get(this.getColumns().indexOf(sortColumn));
		}
		return null;
	}
	
	private int getNumberOfColumns(Map<String, String> request) {
		Pattern p = Pattern.compile("columns\\[[0-9]+\\]\\[data\\]");  

		int i = 0;

		for(Entry<String, String> entry : request.entrySet()) {
			Matcher m = p.matcher(entry.getKey());
			if(m.matches())	{
				++i;
			}
		}
		return i;
	}

	/**
	 * Checks if is collection empty.
	 *
	 * @param collection the collection
	 * @return true, if is collection empty
	 */
	private static boolean isCollectionEmpty(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if is object empty.
	 *
	 * @param object the object
	 * @return true, if is object empty
	 */
	public static boolean isObjectEmpty(Object object) {
		if(object == null) return true;
		else if(object instanceof String) {
			if (((String)object).trim().length() == 0) {
				return true;
			}
		} else if(object instanceof Collection) {
			return isCollectionEmpty((Collection<?>)object);
		}
		return false;
	}

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
	public int getSortColumnId() {
		return sortColumnId;
	}
	public void setSortColumnId(int sortColumnId) {
		this.sortColumnId = sortColumnId;
	}
	public SortOrder getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
	public List<DatatableColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<DatatableColumn> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return "DatatableParameter [draw=" + draw + ", start=" + start + ", length=" + length + ", search=" + search
				+ ", regex=" + regex + ", type=" + type + ", uniqueId=" + uniqueId + ", sortColumn=" + sortColumnId
				+ ", sortOrder=" + sortOrder + ", columns=" + columns + "]";
	}

	
}
