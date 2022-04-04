package com.skydrago.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skydrago.entity.Flight;
import com.skydrago.service.FlightServices;

@Controller
public class FlightController {
	@Autowired
	FlightServices service;
	
	List<String> listFlightNo = Arrays.asList("CHE1", "CBE2", "BAN3","MUM4","DLH5","PNE6","APH7");
	List<String> listDO = Arrays.asList("Chennai", "Coimbatore", "Bangalore","Mumbai","Delhi","Pune","AndhraPradesh");
	//formating 24hr time into 12hr format
	DateFormat idf=new SimpleDateFormat("hh:mm");
	DateFormat odf=new SimpleDateFormat("hh:mm aa");
	
	@GetMapping("/")
	public String index() {
		return "thymeleaf/index";
	}
			
			
	@GetMapping("/home")
	public String home(Model model) {
		
		model.addAttribute("listDO", listDO);
		
		return"thymeleaf/home";
	}
	
	@GetMapping("/homesearch")
	public String home(@RequestParam("destination")String destination,@RequestParam("origin")String origin,@RequestParam("date")String date,Model model) {
		
		model.addAttribute("listDO", listDO);
		
		 List<Flight> flights=service.search(destination, origin, date);
		 for(Flight f:flights) {
			 int totalSeat=f.getSeats().getFirstSeat()+f.getSeats().getBussinessSeat()+f.getSeats().getEconomySeat();
			 if(totalSeat==0) {
				 f.setBooked(true);
			 }
		 }
			  model.addAttribute("list",flights);
			  
		return "thymeleaf/home";
	}
	
	@GetMapping("/input")
	public String inputFlight(Model model) {
		Flight flight=new Flight();
		model.addAttribute(flight);
		
		model.addAttribute("listDO", listDO);
		
		model.addAttribute("listFlightNo", listFlightNo);
		
		return "thymeleaf/flight_register";
	}
	
	@PostMapping("/flightinput")
	public String inputFlight(@Valid @ModelAttribute("flight") Flight flight,BindingResult binding,Model model) throws ParseException {
		if(binding.hasErrors()) {

			model.addAttribute("listDO", listDO);
			
			
			model.addAttribute("listFlightNo", listFlightNo);
			
			return "thymeleaf/flight_register";
		}
		
		
		
		Date date=idf.parse(flight.getFlightTime());
		
		flight.setFlightTime(odf.format(date));
		
		service.save(flight);
		return "redirect:/view";
	}
	
	@RequestMapping("/view")
	public String listAllDetails(Model m) {
		List<Flight> flights=service.listAll();
		for(Flight f:flights) {
			 int totalSeat=f.getSeats().getFirstSeat()+f.getSeats().getBussinessSeat()+f.getSeats().getEconomySeat();
			 if(totalSeat==0) {
				 f.setBooked(true);
			 }
		 }
		m.addAttribute("list",flights);
		return "thymeleaf/flight_view";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveFlight(@ModelAttribute("flight")Flight flight) throws ParseException {
		
		Date date1=idf.parse(flight.getFlightTime());
		  
		flight.setFlightTime(odf.format(date1));
		
		service.save(flight);
		return"redirect:/view";
	}
	
	@RequestMapping("/edit/{id}")
	public String showEditProductPage(@PathVariable("id") int id, Model model) throws ParseException {
		Flight flight = service.get(id);
		
		Date date1=odf.parse(flight.getFlightTime());
		  
		flight.setFlightTime(idf.format(date1));
		
		model.addAttribute("flight", flight);
		
		model.addAttribute("listDO", listDO);
		model.addAttribute("listFlightNo", listFlightNo);
		
		return "thymeleaf/edit_flight";
	}

	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") int id,Model model) {
		try {
		service.delete(id);
		}
		catch(Exception e) {
			model.addAttribute("deleteException","Cannot delete flight after booking");
		}
		return "redirect:/view";		
	}
	
	@GetMapping("/403")
	public String accessDenied() {
		return"forbidden";
	}
	
}
