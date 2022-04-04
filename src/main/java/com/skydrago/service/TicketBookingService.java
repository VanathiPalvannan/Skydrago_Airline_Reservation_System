package com.skydrago.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skydrago.entity.Passenger;
import com.skydrago.repository.TicketBookingRepository;

@Transactional
@Service
public class TicketBookingService {

	@Autowired
	TicketBookingRepository ticketBookingRepository;
	
	public void save(Passenger passenger){
		ticketBookingRepository.save(passenger);
	}
	
	public Passenger getById(long passengerId) {
		return ticketBookingRepository.getById(passengerId);
	}
	
	public List<Passenger> listAll(){
		return ticketBookingRepository.findAll();
	}
	
	public void updateFirstSeat(int noOfSeat, int seat_id) {
		ticketBookingRepository.updateFirstSeat(noOfSeat, seat_id);
	}
	public void updateEconomySeat(int noOfSeat, int seat_id) {
		ticketBookingRepository.updateEconomySeat(noOfSeat, seat_id);
	}
	public void updateBusinessSeat(int noOfSeat, int seat_id) {
		ticketBookingRepository.updateBusinessSeat(noOfSeat, seat_id);
	}
	
	public List<Passenger> listPassengerByFlightId(int id){
		return ticketBookingRepository.getPassengersByFlightId(id);
	}
	
	public List<Passenger> listPassengerByUsername(String username){
		return ticketBookingRepository.getPassengersByUsername(username);
	}
	
	public void updateIsCancel(int id) {
		ticketBookingRepository.updateIsCancel(id);
	}
	
	public void deleteById(int id) {
		ticketBookingRepository.deleteById((long) id);;
	}
	
	public void incrementFirstSeat(int noOfSeat,int seat_id) {
		ticketBookingRepository.incrementFirstSeat(noOfSeat, seat_id);
	}
	
	public void incrementEconomySeat(int noOfSeat,int seat_id) {
		ticketBookingRepository.incrementEconomySeat(noOfSeat, seat_id);
	}
	
	public void incrementBusinessSeat(int noOfSeat,int seat_id) {
		ticketBookingRepository.incrementBusinessSeat(noOfSeat, seat_id);
	}
}
