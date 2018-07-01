package com.bartolay.inventory.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.UserGroup;

@Repository
public interface UserGroupRepository extends CrudRepository<UserGroup, Integer> {
	
//	@Query(value = "SELECT ug FROM UserGroup ug join fetch ug.users where ug.name = :name")
//	public UserGroup findByName(@Param("name") String name);
}
