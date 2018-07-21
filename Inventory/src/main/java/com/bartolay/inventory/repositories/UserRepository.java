package com.bartolay.inventory.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.entity.UserGroup;
import com.bartolay.inventory.enums.AccountType;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    
	public User findByUsername(String username);
	
	@Query(value = "SELECT u FROM User u")
    Iterable<User> apiFindAll();

	public List<User> findAllByUserGroup(UserGroup userGroup);
	
	@Query(value = "SELECT u FROM User u WHERE accountType = :accountType AND (lastname like %:filter% or firstname like %:filter% or username like %:filter%) ") 
	List<User> findAllByTypeAndFilter(@Param("accountType") AccountType accountType, @Param("filter") String filter);
	
//	@Query(value = "SELECT u FROM User u WHERE userGroup = :userGroup AND (lastname like %:filter% or firstname like %:filter% or username like %:filter%) ") 
//	List<User> findAllByUserGroupAndFilter(@Param("userGroup") UserGroup userGroup, @Param("filter") String filter);
//	
////	@Query(value = "SELECT u FROM User u LEFT JOIN FETCH u.userGroup where u.userGroup = :userGroup")
//	List<User> findByUserGroup(@Param("userGroup") UserGroup userGroup);
}
