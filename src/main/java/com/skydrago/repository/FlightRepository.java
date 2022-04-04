package com.skydrago.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skydrago.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer> {
	
	
	
	  @Query("select f from Flight f where f.destination like ?1 and f.origin like ?2 and f.flightDate like ?3")
	  List<Flight> findbyDOAndDate(String destination,String origin,String date);
	 
	 
}
