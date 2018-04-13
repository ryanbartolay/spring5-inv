package com.bartolay.inventory.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(value="/form")
public class FormRestController {

	private static final String FORMPACKAGE = "net.gotech.web.forms.";
	private static final String ENTITYPACKAGE = "net.gotech.web.entities.";

	@RequestMapping(value="/{name}", method=RequestMethod.GET)
	public ObjectNode getFormJSON(@PathVariable String name) {

		try {
			System.out.println("*****" + getForm(name));
			return getForm(name);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private  ObjectNode getForm(String name) throws ClassNotFoundException {
		try {
			Class<?> cls = Class.forName(FORMPACKAGE+name);
			//return html.createJsonObject(cls);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			try {
				Class<?> cls = Class.forName(ENTITYPACKAGE+name);
				//return html.createJsonObject(cls);
			} catch (ClassNotFoundException ex) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
}