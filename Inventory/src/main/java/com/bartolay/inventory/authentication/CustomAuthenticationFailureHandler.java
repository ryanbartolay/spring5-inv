package com.bartolay.inventory.authentication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String client_id = null;
		String redirect_uri = null;
		String response_type = null;
		String state = null;		

		StringBuilder url = new StringBuilder();
		url.append(request.getRequestURL());

		Map<String, String> parameters = new HashMap<>();

		try {
			client_id = request.getParameter("client_id");

			if(client_id.trim().length() > 0) {
				parameters.put("client_id", client_id);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		try {
			redirect_uri = request.getParameter("redirect_uri");
			if(redirect_uri.trim().length() > 0) {
				parameters.put("redirect_uri", redirect_uri);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		try {
			response_type = request.getParameter("response_type");
			if(response_type.trim().length() > 0) {
				parameters.put("response_type", response_type);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		try {
			state = request.getParameter("state");
			if(state.trim().length() > 0) {
				parameters.put("state", state);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}


		if(parameters.size() > 0) {
			url.append("?");

			parameters.forEach((k, v) ->{
				url.append(k + "=" + v + "&");
			});

			url.append("error");
		} else {
			url.append("?error");
		}
		
		response.sendRedirect(url.toString());
	}

}
