package com.bartolay.inventory.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bartolay.inventory.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class StringUtils {

	@Autowired
	private ObjectMapper objectMapper;
	
	private static MessageDigest md;
	
	public String cryptWithMD5(String pass){
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] passBytes = pass.getBytes();
			md.reset();
			byte[] digested = md.digest(passBytes);
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<digested.length;i++){
				sb.append(Integer.toHexString(0xff & digested[i]));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public String userFullnameWithEmail(User user) {
		return user.getLastName() + ", " + user.getFirstName() + " (" + user.getEmail() + ")";
	}
	
	public String base64decode(String raw) {
		return base64decode(raw.getBytes(StandardCharsets.UTF_8));
	}
	
	public String base64decode(byte[] raw) {
		return Base64.getUrlEncoder().encodeToString(raw);
	}
	
	
	public String encode(String str) throws JsonProcessingException, UnsupportedEncodingException {
		String msg = objectMapper.writeValueAsString(str);
		return URLEncoder.encode( msg == null ? "" : msg.replace(" ", "%20"), "UTF-8");
	}
	
	public String encode(JSONObject object) throws JsonProcessingException, UnsupportedEncodingException {
		return encode(object.toString());
	}
	
	public String encode(Object str) throws JsonProcessingException, UnsupportedEncodingException {
		String msg = objectMapper.writeValueAsString(str);
		return URLEncoder.encode( msg == null ? "" : msg.replace(" ", "%20"), "UTF-8");
	}
	
}
