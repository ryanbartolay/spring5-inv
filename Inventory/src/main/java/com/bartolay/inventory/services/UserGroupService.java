package com.bartolay.inventory.services;

import java.util.Map;

import com.bartolay.inventory.entity.UserGroup;

public interface UserGroupService {

	UserGroup retrieveByName(String name);
	
	Object retrieveDatatableList(Map<String, String> requestMap);

}
