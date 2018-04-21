package com.bartolay.inventory.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	private String authority;
	
	public Authority(String authority) {
		super();
		this.authority = authority;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Override
	public String toString() {
		return "Authority [authority=" + authority + "]";
	}
}
