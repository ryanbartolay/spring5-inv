package com.bartolay.inventory.authentication;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private RedirectStrategy redirect;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("====================Custom Authentication Success START =========================");
		String tmp_query_string = null;

		Map<String, String[]> query_string = request.getParameterMap();
		System.out.println("query_string " + query_string);

	
		// if redirect uri is existing
		// Redirecting and installing Query String
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		
		for(Map.Entry<String, String[]> entry : query_string.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue()[0]);
		}
		
		System.out.println("====================Custom Authentication Success END =========================");
		
		if(query_string.keySet().contains("redirect_uri") && query_string.get("redirect_uri")[0].toString().trim().length() > 0) {
			String redirect_uri = query_string.get("redirect_uri")[0].toString();
			
			if(tmp_query_string != null) {
				redirect_uri += "?" + tmp_query_string;
			}
			
			redirect.sendRedirect(request, response, redirect_uri);
		} else {
			redirect.sendRedirect(request, response, "/dashboard");
		}
	}
}
