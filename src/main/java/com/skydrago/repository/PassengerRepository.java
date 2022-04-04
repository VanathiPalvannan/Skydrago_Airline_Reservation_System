package com.skydrago.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skydrago.entity.Passenger;
@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
	List<Passenger> findByMobileNumber(long mobileNumber);
	List<Passenger> findByUserName(String userName);
	
}
