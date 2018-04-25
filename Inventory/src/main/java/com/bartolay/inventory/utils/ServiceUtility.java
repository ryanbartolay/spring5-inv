package com.bartolay.inventory.utils;

import java.util.ArrayList;
import java.util.List;

public class ServiceUtility {

	public static <E> List<E> toList(Iterable<E> elements) {
		List<E> list = new ArrayList<>();
		for(E e : elements) {
			list.add(e);
		}
		return list;
	}
}
