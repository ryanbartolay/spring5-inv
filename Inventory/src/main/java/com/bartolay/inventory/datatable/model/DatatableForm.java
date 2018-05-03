package com.bartolay.inventory.datatable.model;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.bartolay.inventory.datatable.SortOrder;

@Component
public class DatatableForm {

	public static final String DRAW = "draw";
	public static final String START = "start";
	public static final String LENGTH = "length";

	public static final String UNIQUE_ID = "_";
	public static final String SEARCH = "search[value]";
	public static final String REGEX = "search[regex]";

	public static final String SORT_COLUMN = "order[0][column]";
	public static final String SORT_ORDER = "order[0][dir]";

	private static final String BLANK = "";
	private static final String SPACE = " ";
	private static final String LIKE_PREFIX = " LIKE '%";
	private static final String LIKE_SUFFIX = "%' ";
	private static final String AND = " AND ";
	private static final String OR = " OR ";
	private static final String ORDER_BY = " ORDER BY ";
	private static final String BRKT_OPN = " ( ";
	private static final String BRKT_CLS = " ) ";
	private static final String COMMA = " , ";

	public DatatableParameter getFormData(Map<String, String> request) {

		DatatableParameter parameter = null;
		
		if(!request.isEmpty()) {

			parameter = new DatatableParameter();
			
			parameter.setDraw(Integer.parseInt(request.get(DRAW).toString()));
			parameter.setStart(Integer.parseInt(request.get(START).toString()));
			parameter.setLength(Integer.parseInt(request.get(LENGTH).toString()));

			parameter.setUniqueId(request.get(UNIQUE_ID).toString());
			parameter.setSearch(request.get(SEARCH));
			parameter.setRegex(Boolean.valueOf(request.get(REGEX).toString()));

			parameter.setSortColumn(Integer.parseInt(request.get(SORT_COLUMN).toString()));
			parameter.setSortOrder(SortOrder.valueOf(request.get(SORT_ORDER).toUpperCase().trim()));

			int numberOfColumns = getNumberOfColumns(request);
			System.err.println("numberOfColumns " + numberOfColumns);


			Set<DatatableColumn> columns = new TreeSet<>();
			
			for(int i=0; i < numberOfColumns; i++) {
				if(null != request.get("columns["+ i +"][data]") 
						&& !"null".equalsIgnoreCase(request.get("columns["+ i +"][data]"))  
						&& !isObjectEmpty(request.get("columns["+ i +"][data]"))) {
					
					DatatableColumn column = new DatatableColumn();
					column.setData(request.get("columns["+ i +"][data]"));
					column.setName(request.get("columns["+ i +"][name]"));
					column.setOrderable(Boolean.valueOf(request.get("columns["+ i +"][orderable]")));
					column.setRegex(Boolean.valueOf(request.get("columns["+ i +"][search][regex]")));
					column.setSearch(request.get("columns["+ i +"][search][value]"));
					column.setSearchable(Boolean.valueOf(request.get("columns["+ i +"][searchable]")));
					
					columns.add(column);
				} 
			}
			
			parameter.setColumns(columns);

		}
		
		return parameter;
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
}