package com.devioooh.srt.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ContactForm {
	
	@NotEmpty
	@Size(max=50)
	private String firstName;
	@NotEmpty
	@Size(max=100)
	private String lastName;
	@NotEmpty
	@Size(max=20)
	private String phone;
	@NotEmpty
	@Email
	@Size(max=100)
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}