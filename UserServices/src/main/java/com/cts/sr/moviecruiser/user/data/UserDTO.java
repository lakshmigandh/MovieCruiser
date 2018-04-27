package com.cts.sr.moviecruiser.user.data;

import java.util.Date;

import com.cts.sr.moviecruiser.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserDTO {

	@JsonProperty("id")
	private String userId;
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("password")
	private String password;
	

	public UserDTO(String userId, String firstName, String lastName, String email,String password, Date createdDate) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public UserDTO() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void copy(User user){
		
		if(user.getUserId() != null)
			this.setUserId(user.getUserId());
		if(user.getFirstName() != null)
			this.setFirstName(user.getFirstName());
		if(user.getLastName() != null)
			this.setLastName(user.getLastName());
		if(user.getEmail() != null)
			this.setEmail(user.getEmail());
		if(user.getPassword() != null)
			this.setPassword(user.getPassword());
		
	}
}
