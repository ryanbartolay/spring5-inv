package com.bartolay.inventory.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bartolay.inventory.entity.User;
import com.bartolay.inventory.repositories.UserRepository;
import com.bartolay.inventory.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class UserRestController {

	@Autowired
	private UserService<User> userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/api/users", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getList() {
		List<User> users = new ArrayList<>();

		for(User user : userRepository.apiFindAll()) {
			users.add(user);
		}
		
		return ResponseEntity.ok(users);
	}
	
	@RequestMapping(value="/api/datatable/users", method=RequestMethod.GET, produces="application/json")
	public String datatableBrand(@RequestParam Map<String, String> requestMap) throws JsonProcessingException, UnsupportedEncodingException {
		return userService.retrieveDatatableList(requestMap).toString();
	}
	
//	@RequestMapping(value="/create", method=RequestMethod.GET)
//	public ModelAndView createForm(Model model) {
//		ModelAndView mav = new ModelAndView("user/edit");
//		return mav;
//	}
//	
//	@RequestMapping(value="/create", method=RequestMethod.POST)
//	public ModelAndView createUser(@Valid User user, BindingResult bindingResult, Model model) {
//		System.out.println("create user : " + user);
//		
//		ModelAndView mav = new ModelAndView();
//		if (bindingResult.hasErrors()) {
//			mav.setViewName("user/edit");
//			return mav;
//		}
//		
//		try {
//			employeeService.create(user);	
//		} catch(Exception e) {
//			bindingResult.reject("error.app.binding.webform", "Error!!!");
//		}
//		
//		mav.setViewName("user/edit_profile");
//		return mav;
//	}
//	
//	
//	@RequestMapping(value="/edit/{username}", method=RequestMethod.GET)
//	public ModelAndView create(Model model, @PathVariable String username) {
//		//userService.
//		//model.addAttribute("users", userService.getAll());
//		ModelAndView mav = new ModelAndView("user/edit");
//		return mav;
//	}
//
//	@RequestMapping(value="/{username}", method=RequestMethod.GET)
//	public String viewUserByUsername(ModelMap map, @PathVariable("username") String username) {
//		return "users/view_single";
//	}
}
