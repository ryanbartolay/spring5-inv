package com.bartolay.inventory.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.form.UserForm;
import com.bartolay.inventory.model.ApiResponse;
import com.bartolay.inventory.model.RestApiException;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.services.UserService;
import com.bartolay.inventory.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class UserRestController {

	@Autowired
	private UserService<User> userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StringUtils stringUtils;
	
	@RequestMapping(value="/api/users", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getList() {
		List<User> users = new ArrayList<>();

		for(User user : userRepository.apiFindAll()) {
			users.add(user);
		}
		
		return ResponseEntity.ok(users);
	}
	
	@RequestMapping(value="/api/users/{id}", method=RequestMethod.GET)
	public String getById(@PathVariable Integer id) {
		try {
			Optional<User> user = userRepository.findById(id);
			return stringUtils.encode(user.get());
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping(value="/api/datatable/users", method=RequestMethod.GET, produces="application/json")
	public String datatableBrand(@RequestParam Map<String, String> requestMap) throws JsonProcessingException, UnsupportedEncodingException {
		return userService.retrieveDatatableList(requestMap).toString();
	}
	
	@RequestMapping(value="/api/users", method=RequestMethod.POST)
	public String create(@Valid UserForm userForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		ApiResponse response = null;
		
		try {
			User user = userService.create(userForm);

			response = new ApiResponse(HttpStatus.OK, "Succesfully created " + user.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}

		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/users", method=RequestMethod.PUT)
	public String update(@Valid UserForm userForm, BindingResult bindingResult) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		
		if (bindingResult.hasErrors()) {
			throw new RestApiException(bindingResult);
		}

		try {
			User user = userService.update(userForm);
			response = new ApiResponse(HttpStatus.OK, "Succesfully updated " + user.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		return stringUtils.encode(response);
	}
	
	@RequestMapping(value="/api/users/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable Integer id) throws RestApiException, JsonProcessingException, UnsupportedEncodingException {

		ApiResponse response = null;
		try {
			User user = userService.delete(id);

			response = new ApiResponse(HttpStatus.ACCEPTED, "Record deleted " + user.getName());
		} catch(Exception e) {
			response = new ApiResponse(HttpStatus.BAD_REQUEST, e.getMessage());
			
		}
		return stringUtils.encode(response);
	}

}
