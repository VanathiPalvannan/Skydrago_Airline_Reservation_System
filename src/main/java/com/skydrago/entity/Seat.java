package com.skydrago.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Seat {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="class_id")
	private int id;
	private int firstSeat;
	private double firstCost;
	private int bussinessSeat;
	private double bussinessCost;
	private int economySeat;
	private double economyCost;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFirstSeat() {
		return firstSeat;
	}
	public void setFirstSeat(int firstSeat) {
		this.firstSeat = firstSeat;
	}
	public double getFirstCost() {
		return firstCost;
	}
	public void setFirstCost(double firstCost) {
		this.firstCost = firstCost;
	}
	public int getBussinessSeat() {
		return bussinessSeat;
	}
	public void setBussinessSeat(int bussinessSeat) {
		this.bussinessSeat = bussinessSeat;
	}
	public double getBussinessCost() {
		return bussinessCost;
	}
	public void setBussinessCost(double bussinessCost) {
		this.bussinessCost = bussinessCost;
	}
	public int getEconomySeat() {
		return economySeat;
	}
	public void setEconomySeat(int economySeat) {
		this.economySeat = economySeat;
	}
	public double getEconomyCost() {
		return economyCost;
	}
	public void setEconomyCost(double economyCost) {
		this.economyCost = economyCost;
	}
	
	
	
	
	
	
	
}
