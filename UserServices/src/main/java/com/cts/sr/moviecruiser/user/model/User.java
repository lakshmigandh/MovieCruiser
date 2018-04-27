package com.cts.sr.moviecruiser.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.cts.sr.moviecruiser.user.data.UserDTO;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;

	@CreationTimestamp
	@Column(name = "created_date")
	private Date createdDate;
	

	public User(String userId, String firstName, String lastName,String email, String password, Date createdDate) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.createdDate = createdDate;
	}

	public User() {
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	public void copy(UserDTO user){
		
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
