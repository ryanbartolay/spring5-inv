package com.bartolay.inventory.services;

import java.util.ArrayList;
import java.util.List;

public interface ServiceUtility<E> {
	default List<E> toList(Iterable<E> elements) {
		List<E> list = new ArrayList<>();
		for(E e : elements) {
			list.add(e);
		}
		return list;
	}
}
