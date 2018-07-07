package com.bartolay.inventory.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController {
	
	public static final String TEMPLATE_COLLAPSE = "collapse";

	@RequestMapping(path="/api/template/collapse/{template_collapse}")
	public String collapseLayout(HttpSession session, @PathVariable Boolean template_collapse) {
		session.setAttribute(TEMPLATE_COLLAPSE, template_collapse);
		
		return "success";
	}
}
