package com.bartolay.inventory.datatable.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class DatatableParameter {
	
	private Map<String, String> requestMap;
	
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
	
	private JsonNode form;

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

		this.requestMap = requestMap;
		
		this.setDraw(getIntegerValue(DRAW));
		this.setStart(getIntegerValue(START));
		this.setLength(getIntegerValue(LENGTH));

		this.setUniqueId(getStringValue(UNIQUE_ID));
		this.setSearch(getStringValue(SEARCH));
		
		this.setRegex(getBooleanValue(REGEX));

		this.setSortColumnId(getIntegerValue(SORT_COLUMN));
		this.setSortOrder(SortOrder.valueOf(getStringValue(SORT_ORDER).toUpperCase().trim()));

		int numberOfColumns = getNumberOfColumns(requestMap);


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
		
		if(requestMap.get("form") != null) {
			try {
				this.form = new ObjectMapper().readTree(requestMap.get("form").toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		this.setColumns(columns);
	}
	
	/**
	 * Returns string value from request parameter
	 * @param index
	 * @return
	 */
	private String getStringValue(String index) {
		try {
			String str = this.requestMap.get(index).trim().toString();
			if(str.length() > 0) {
				return str;
			}
		} catch(Exception e) {
//			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Returns integer from request parameter
	 * @param index
	 * @return
	 */
	private int getIntegerValue(String index) {
		String strValue = getStringValue(index);
		
		if(strValue != null) {
			try {
				return Integer.parseInt(strValue);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}		
		return 0;
	}

	/**
	 * Returns boolean from request parameter
	 * @param index
	 * @return
	 */
	private boolean getBooleanValue(String index) {
		String strValue = getStringValue(index);
		
		if(strValue != null) {
			try {
				return Boolean.parseBoolean(strValue);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}		
		return false;
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
	
	public JsonNode getForm() {
		return form;
	}

	@Override
	public String toString() {
		return "DatatableParameter [draw=" + draw + ", start=" + start + ", length=" + length + ", search=" + search
				+ ", regex=" + regex + ", type=" + type + ", uniqueId=" + uniqueId + ", sortColumn=" + sortColumnId
				+ ", sortOrder=" + sortOrder + ", columns=" + columns + "]";
	}

	
}
