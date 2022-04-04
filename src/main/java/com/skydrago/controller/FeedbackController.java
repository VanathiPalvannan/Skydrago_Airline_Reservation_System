package com.skydrago.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.skydrago.entity.FeedBack;
import com.skydrago.service.FeedbackService;


@Controller
public class FeedbackController {
	
	@Autowired
	FeedbackService service;
	
	 @GetMapping("/feedback")
	    public String showForm(Model model) {
	        FeedBack fb = new FeedBack();
	        model.addAttribute("feedback", fb);
	        return "thymeleaf/feedback";
	    }
	 
	 
	 @PostMapping("/feedback") 
	  public String userFeedback(@Valid @ModelAttribute("feedback")FeedBack feedback,BindingResult binding,Model model) {
		  if(binding.hasErrors()) {
			  return"thymeleaf/feedback";
		  }
		  else {
			  service.save(feedback);
			  return "thymeleaf/home";
		  }
		
	 }
	 
	 
	 @GetMapping("/feedbackview")
	    public String showFeedback(Model model) {
	        List<FeedBack> feedbacks = service.getAllFeedbacks();
	        model.addAttribute("list", feedbacks);
	        return "thymeleaf/feedback_view";
	    }
	 
	 	@GetMapping("/aboutus")
		public String aboutUs() {
			return"thymeleaf/about_us";
		}
}
