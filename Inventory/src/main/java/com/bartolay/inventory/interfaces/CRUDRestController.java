package com.bartolay.inventory.interfaces;

public interface CRUDRestController<K> {
	public Iterable<K> getList();
	public K getRecordById(Long id);
	public K create(K k);
	public K update(K k);
}
