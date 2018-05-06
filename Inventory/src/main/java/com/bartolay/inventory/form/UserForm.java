package com.bartolay.inventory.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserForm {
	private Long id;
	
	@NotNull
	@Size(min=5, max=100, message="Username length minimum 5 and 100")
	private String username;
	
	@NotNull(message="Firstname is required")
	private String firstname;
	
	@NotNull(message="Lastname is required")
	private String lastname;
	
	@Email(message="Invalid email format")
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
