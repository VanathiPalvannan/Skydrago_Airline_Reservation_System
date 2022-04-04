package com.skydrago.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Flight {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="flight_id")
	private int id;
	
	@NotBlank
	private String origin;
	
	@NotBlank
	private String destination;
	
	@NotBlank
	private String duration;
	
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	private String flightDate;
	
	@NotBlank
	private String flightNumber;
	
	@NotBlank
	private String flightTime;
	
	private boolean isBooked=false;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="seat_id")
	private Seat seats;
	
	@OneToMany(mappedBy="flight")
	private List<Passenger> passenger;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}

	public boolean isBooked() {
		return isBooked;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public Seat getSeats() {
		return seats;
	}

	public void setSeats(Seat seats) {
		this.seats = seats;
	}

	public List<Passenger> getPassenger() {
		return passenger;
	}

	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}
	
	
}
