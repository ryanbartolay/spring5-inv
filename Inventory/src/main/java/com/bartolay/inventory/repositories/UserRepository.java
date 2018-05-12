package com.bartolay.inventory.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    
	public User findByUsername(String username);
	
	@Query(value = "SELECT u FROM User u")
    Iterable<User> apiFindAll();
}
