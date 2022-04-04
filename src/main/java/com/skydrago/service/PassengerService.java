package com.skydrago.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skydrago.entity.Passenger;
import com.skydrago.repository.PassengerRepository;

@Service
public class PassengerService {
	
	@Autowired
	private PassengerRepository passengerDao;
	
	public List<Passenger> getAllPassengers() {
		return passengerDao.findAll();
	}
	
	
	public Passenger findById(Long id) {
		return passengerDao.findById(id).get();
	}
	
	public List<Passenger> findByUsername(String username) {
		return passengerDao.findByUserName(username);
	}
	
	public List<Passenger> findByMobileNumber(Long mobilenumber) {
		return passengerDao.findByMobileNumber(mobilenumber);
	}
	
	public void saveAll(List<Passenger> passengers) {
		passengerDao.saveAll(passengers);
	}
	
	public void save(Passenger passenger) {
		passengerDao.save(passenger);
	}

}
