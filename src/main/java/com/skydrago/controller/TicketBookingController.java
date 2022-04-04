package com.skydrago.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skydrago.entity.Flight;
import com.skydrago.entity.Passenger;
import com.skydrago.entity.User;
import com.skydrago.service.FlightServices;
import com.skydrago.service.TicketBookingService;

@Controller
public class TicketBookingController {

	@Autowired
	FlightServices flightService;
	
	@Autowired
	TicketBookingService ticketBookingService;
	
	private String getCurrentUser() {
		
		String username;
		  Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  if(principal instanceof UserDetails) {
			  username=((UserDetails)principal).getUsername();
		  }else {
			  username=principal.toString();
		  }
		return username;
	}
	
	@RequestMapping("/viewFlightDetail")
	public String viewFlightDetail(Model model) {
		List<Flight> flightDetails= flightService.listAll();
		model.addAttribute("flightDetails",flightDetails);
		return "jsp/flight_detail_view";
	}
	
	@RequestMapping("/bookdetail/{flightid}")
	public String bookDetail(@PathVariable int flightid,Model model ,@ModelAttribute("passenger") Passenger passenger) {
		
		String username;
		  Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  if(principal instanceof UserDetails) {
			  username=((UserDetails)principal).getUsername();
		  }else {
			  username=principal.toString();
		  }
		  model.addAttribute("username",username);
		
		Flight flight=flightService.getById(flightid);
		model.addAttribute("flight",flight);
		return "jsp/ticket_booking";
	}
	
	@RequestMapping("/seats/{id}")
	public String includeSeatAndClass(@PathVariable int id,Model model,@RequestParam("noOfSeats") int noOfSeats,@RequestParam("flightClass") String flightClass,@ModelAttribute("passenger") Passenger passenger) {
		Flight flight=flightService.getById(id);
		model.addAttribute("flight",flight);
		model.addAttribute("seats",noOfSeats);
		
		String username;
		  Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  if(principal instanceof UserDetails) {
			  username=((UserDetails)principal).getUsername();
		  }else {
			  username=principal.toString();
		  }
		  model.addAttribute("username",username);
		
		model.addAttribute("flightClass",flightClass);
		return "jsp/ticket_booking";
	}
	
	@RequestMapping(value="/storePassenger/{noOfSeatsBooked}/{flightid}/{flight_Class}" )
	public String yourBooking(@Valid @ModelAttribute("passenger") Passenger passenger,BindingResult bindingResult,@PathVariable("flight_Class") String flight_Class,@PathVariable("noOfSeatsBooked") int noOfSeat,@PathVariable("flightid") int flightId,RedirectAttributes redirectAttribute,Model model) {
		Flight flight=flightService.get(flightId);
		System.out.print(flightId);
		if(bindingResult.hasErrors()) {
			   System.out.print("Inside Binding results");
			   model.addAttribute("flightClass",flight_Class);
			   model.addAttribute("flight",flight);
			   String username=getCurrentUser();
			   model.addAttribute("username",username);
			   model.addAttribute("seats",noOfSeat);
			   return "jsp/ticket_booking";
		   }
		int seatId=flight.getSeats().getId();
		int firstSeat=flight.getSeats().getFirstSeat();
		int businessSeat=flight.getSeats().getBussinessSeat();
		int economySeat=flight.getSeats().getEconomySeat();
		String flightClass=passenger.getFlightClass();
		if(noOfSeat<=firstSeat && flightClass.equals("First Class")) {
			ticketBookingService.updateFirstSeat(noOfSeat, seatId);
		}
		else if(noOfSeat<=businessSeat && flightClass.equals("Business Class")) {
			ticketBookingService.updateBusinessSeat(noOfSeat, seatId);
		}
		else if(noOfSeat<=economySeat && flightClass.equals("Economy Class")) {
			ticketBookingService.updateEconomySeat(noOfSeat, seatId);
		}
		else {
			return "redirect:/bookdetail/{flightid}";
		}
		
		String username=getCurrentUser();
		passenger.setUserName(username);
		
		ticketBookingService.save(passenger);
		redirectAttribute.addAttribute("passengerId", passenger.getId());
		return "redirect:/viewBookedDetail/{passengerId}";
	}
	@RequestMapping("/viewBookedDetail/{passengerId}")
	public String viewBookedDetail(@PathVariable long passengerId,Model model) {
		List<Flight> flight=flightService.listAll();
		Passenger passenger=ticketBookingService.getById(passengerId);
		model.addAttribute("flight", flight);
		
		  String username=getCurrentUser();
		  model.addAttribute("username",username);
		
		model.addAttribute("passenger",passenger);
		return "jsp/booked_details";
	}
	
	@RequestMapping("/bookedPassengers/{id}")
	public String listBookedPassengersById(@PathVariable("id")int id,Model model){
		List<Passenger> passengers=ticketBookingService.listPassengerByFlightId(id);
		model.addAttribute("list",passengers);
		return"thymeleaf/passenger_view";
	}
	
	@RequestMapping("/ticketsbooked")
	public String listPassengersByUsername(Model model){
		
		String username=getCurrentUser();
		
		List<Passenger> passengers=ticketBookingService.listPassengerByUsername(username);
		model.addAttribute("list",passengers);
		
		return"thymeleaf/tickets_booked";
	}
	
	@RequestMapping("/isCancel/{id}")
	public String setIsCancel(@PathVariable("id")int id){
		ticketBookingService.updateIsCancel(id);
		return"thymeleaf/tickets_booked";
	}
	
	@RequestMapping("/passengerdelete/{id}")
	public String deleteProduct(@PathVariable("id") int id) {
		Passenger passenger=ticketBookingService.getById(id);
		String flightClass=passenger.getFlightClass();
		int noOfSeats=passenger.getNoOfSeatsBooked();
		int seat_id=passenger.getFlight().getSeats().getId();
		System.out.println(seat_id);
		if(flightClass.equals("Economy Class")) {
			ticketBookingService.incrementEconomySeat(noOfSeats, seat_id);
		}
		else if(flightClass.equals("Business Class")) {
			ticketBookingService.incrementBusinessSeat(noOfSeats, seat_id);
		}
		else if(flightClass.equals("First Class")) {
			ticketBookingService.incrementFirstSeat(noOfSeats, seat_id);
		}
		ticketBookingService.deleteById(id);
		return "redirect:/view";		
	}
	
	@GetMapping("/searchPassengerById")
	public String searchById(@RequestParam("passengerid")long id,Model model) {
		Passenger passenger= ticketBookingService.getById(id);
		List<Passenger> passengers=new ArrayList<>();
		passengers.add(passenger);
		model.addAttribute("list",passengers);
		return"thymeleaf/passenger_view";
	}
	
}
