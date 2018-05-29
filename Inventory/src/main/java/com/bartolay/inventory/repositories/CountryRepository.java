package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bartolay.inventory.entity.Country;

public interface CountryRepository extends CrudRepository<Country, Integer> {

}
