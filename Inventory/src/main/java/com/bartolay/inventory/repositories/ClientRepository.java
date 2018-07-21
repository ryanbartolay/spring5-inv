package com.bartolay.inventory.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

}
