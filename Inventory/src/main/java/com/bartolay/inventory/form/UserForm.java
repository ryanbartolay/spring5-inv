package com.bartolay.inventory.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserForm {
	private Integer id;
	
	@NotNull
	@Size(min=5, max=100, message="Username length minimum 5 and 100")
	private String username;
	
	@NotNull(message="Firstname is required")
	private String firstName;
	
	@NotNull(message="Lastname is required")
	private String lastName;
	
	@Email(message="Invalid email format")
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
