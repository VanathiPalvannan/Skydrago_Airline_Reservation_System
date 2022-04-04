package com.skydrago.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class FeedBack {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotBlank
	private String name;
	
	@Email(message="Enter valid email address")
	private String email;
	
	private long phno;
	
	@NotBlank(message="Please select any one")
	private String satisfaction;
	
	@NotBlank(message="Please select any one")
	private String service;
	
	private String comments;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhno() {
		return phno;
	}
	public void setPhno(long phno) {
		this.phno = phno;
	}
	public String getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	

}
