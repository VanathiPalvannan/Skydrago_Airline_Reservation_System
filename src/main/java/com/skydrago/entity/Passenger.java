package com.skydrago.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Passenger {
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	private long mobileNumber;
	
	@NotBlank(message="Please select any one")
	private String gender;
	
	@NotBlank
	@Email(message="Enter valid email address")
	private String emailId;
	
	private String flightClass;
	
	private int noOfSeatsBooked;
	
	private double priceOfTicket;
	
	private String userName;
	
	private boolean isCancel;
	
	@ManyToOne(targetEntity=Flight.class)
	@JoinColumn(name="flightId")
	private Flight flight;
	
	@OneToOne(cascade =  CascadeType.ALL)
	@JoinColumn(name="checkinId")
	private Checkin checkIn;
	
	public Checkin getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Checkin checkIn) {
		this.checkIn = checkIn;
	}

	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	
	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public int getNoOfSeatsBooked() {
		return noOfSeatsBooked;
	}

	public void setNoOfSeatsBooked(int noOfSeatsBooked) {
		this.noOfSeatsBooked = noOfSeatsBooked;
	}

	public double getPriceOfTicket() {
		return priceOfTicket;
	}

	public void setPriceOfTicket(double priceOfTicket) {
		this.priceOfTicket = priceOfTicket;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	
	
}
