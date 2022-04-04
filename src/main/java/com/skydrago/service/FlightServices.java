package com.skydrago.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skydrago.entity.Flight;
import com.skydrago.repository.FlightRepository;

@Service
@Transactional
public class FlightServices {
	
	@Autowired
	FlightRepository repo;
	
	public List<Flight> listAll(){
		return repo.findAll();
	}
	
	public Flight getById(int id) {
		return repo.getById(id);
	}
	
	public Flight get(int id) {
		return repo.findById(id).get();
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
	
	public void save(Flight flight) {
		repo.save(flight);
	}
	
	
	
	  public List<Flight> search(String destination,String origin,String date) {
	  return repo.findbyDOAndDate(destination, origin, date);
	  
	  }
	 
	 
	
}
