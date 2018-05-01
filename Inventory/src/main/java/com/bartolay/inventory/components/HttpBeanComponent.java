package com.bartolay.inventory.components;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class HttpBeanComponent {

	@Autowired
	protected HttpServletRequest httpServletRequest;
	@Autowired
	protected HttpServletResponse httpServletResponse;
	
	public HttpSession getSession() {
		return httpServletRequest.getSession();
	}
	
	public void createSession(String key, Object obj) {
		getSession().setAttribute(key, obj);
	}
}