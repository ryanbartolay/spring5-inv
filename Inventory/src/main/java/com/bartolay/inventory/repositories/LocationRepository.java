package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

}
