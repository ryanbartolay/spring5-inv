package com.bartolay.inventory.development;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.entity.UserGroup;
import com.bartolay.inventory.repositories.UserGroupRepository;
import com.bartolay.inventory.repositories.UserRepository;

@Component
public class _10UserGroupsBootstrap implements ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserGroupRepository userGroupRepository;
	
	@Override
	public int getOrder() {
		return 10;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
//		User admin = userRepository.findByUsername("admin");
//		List<User> users = new ArrayList<>();
//		User root = userRepository.findByUsername("root");
//		
//		UserGroup userGroup = new UserGroup();
//		userGroup.setName("Root Users");
//		userGroup.setCreatedBy(root);
//		userGroup.setUsers(users);
//		
//		
//		root.setUserGroup(userGroup);
//		
//		users.add(root);
//		
//		
//		userGroupRepository.save(userGroup);
//		userRepository.save(root);
//		
//		users.clear();
//		
//		
//		userGroup = new UserGroup();
//		userGroup.setName("Administrators");
//		userGroup.setCreatedBy(root);
//		userGroup.setUsers(users);
//		
//		admin.setUserGroup(userGroup);
//		users.add(admin);
//		
//		
//		userGroupRepository.save(userGroup);
//		userRepository.save(admin);
//		
//		users.clear();
//		
//		userGroup = new UserGroup();
//		userGroup.setName("Sales");
//		userGroup.setCreatedBy(root);
//		userGroup.setUsers(users);
//		
//		User sales1 = userRepository.findByUsername("sales1"); 
//		User sales2 = userRepository.findByUsername("sales2");
//		
//		sales1.setUserGroup(userGroup);
//		sales2.setUserGroup(userGroup);
//		
//		users.add(sales1);
//		users.add(sales2);
//		
//		
//		userGroupRepository.save(userGroup);
//		userRepository.saveAll(users);
//		
//		users.clear();
//		
//		userGroup = new UserGroup();
//		userGroup.setName("Customers");
//		userGroup.setCreatedBy(root);
////		userGroup.setUsers(users);
////		
////		User customer = userRepository.findByUsername("customer"); 
////		
////		customer.setUserGroup(userGroup);
////		customer.setUserGroup(userGroup);
////		
////		users.add(sales1);
////		users.add(sales2);
////		
////		
//		userGroupRepository.save(userGroup);
////		userRepository.saveAll(users);
//		
//		users.clear();
//		
//		userGroup = new UserGroup();
//		userGroup.setName("Suppliers");
//		userGroup.setCreatedBy(root);
////		userGroup.setUsers(users);
//		
//		userGroupRepository.save(userGroup);
	}

}
