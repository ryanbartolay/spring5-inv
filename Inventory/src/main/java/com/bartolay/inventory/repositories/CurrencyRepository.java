package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Currency;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Integer> {
	
	public Currency findByCode(String code);

}
