package com.skydrago.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skydrago.entity.Passenger;
import com.skydrago.service.PassengerService;

@Controller
public class CheckinController {
	
	@Autowired
	private PassengerService service;
	
	@GetMapping("/passengersearch")
	public String passengerSearch() {
		return"thymeleaf/passenger_search";
	}
	
	@GetMapping("/searchById")
	public String searchById(@RequestParam("passengerid")long id,Model model) {
		try {
		Passenger passenger= service.findById(id);
		List<Passenger> passengers=new ArrayList<>();
		passengers.add(passenger);
		model.addAttribute("list",passengers);
		
		}
		catch(Exception e) {
			model.addAttribute("error",e.getMessage());
		}
		return"thymeleaf/passenger_search";
	}
	
	@GetMapping("/searchByUserName")
	public String searchByUserName(@RequestParam("username")String username,Model model) {
		List<Passenger> passenger= service.findByUsername(username);
		model.addAttribute("list",passenger);
		return"thymeleaf/passenger_search";
	}
	
	@GetMapping("/searchByMobileNumber")
	public String searchByMobileNumber(@RequestParam("mobilenumber")long number,Model model) {
	try {
		List<Passenger> passenger=service.findByMobileNumber(number);
		model.addAttribute("list",passenger);
	}
	catch(Exception e) {
		model.addAttribute("errorPhno",e.getMessage());
	}
		return "thymeleaf/passenger_search";
	}
	
	@GetMapping("/checkin/{id}")
	public String checkinFormSubmit(@PathVariable("id")int id,Model model) {
		Passenger passenger= service.findById((long) id);
		model.addAttribute("passenger",passenger);
		return "thymeleaf/checkin";
	}
	
	@PostMapping("/checkinsave")
	public String checkinSave(@Valid @ModelAttribute("passenger")Passenger passenger,BindingResult binding,Model model) {
		service.save(passenger);
		return "redirect:/passengersearch";
		}
	}
