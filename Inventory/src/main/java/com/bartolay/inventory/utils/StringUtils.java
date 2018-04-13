package com.bartolay.inventory.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class StringUtils {

	private static MessageDigest md;
	
	public static String cryptWithMD5(String pass){
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
	
	public static String base64decode(String raw) {
		return base64decode(raw.getBytes(StandardCharsets.UTF_8));
	}
	
	public static String base64decode(byte[] raw) {
		return Base64.getUrlEncoder().encodeToString(raw);
	}
}
