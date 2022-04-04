package com.skydrago.controller;


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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skydrago.entity.User;
import com.skydrago.service.UserDetailsServiceImp;

@Controller
public class UserController {
	
	
	
	@Autowired
	UserDetailsServiceImp uservice;
	
	@GetMapping("/userregister")
	public String userPage(Model model) {
		User user=new User();
		model.addAttribute("user", user);
		return"thymeleaf/user_register";
	}
	
	
	  @PostMapping("/saveuserreg") 
	  public String userRegister(@Valid @ModelAttribute("user")User user,BindingResult binding,Model model) {
		  if(binding.hasErrors()) {
			  return"thymeleaf/user_register";
		  }
		  if(user.getPassword().equals(user.getrPassword())) {
				try {
				uservice.save(user);
				return "redirect:/login";
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
					model.addAttribute("errormsg", "Username,Addhar number, Mobile number and mail id must be unique!!");
					return "thymeleaf/user_register";
				}
				}
			else {
				model.addAttribute("passerror","Your password and Re-type password is not match!!! Please check");
			}
 
	  return "user_register";
	  
	  }
	  
	  @PostMapping("/saveuseredit") 
	  public String saveUserEdit(@Valid @ModelAttribute("user")User user,BindingResult binding,Model model) {
		  if(binding.hasErrors()) {
			  return"thymeleaf/edit_user";
		  }
		  if(user.getPassword().equals(user.getrPassword())) {
				try {
				uservice.save(user);
				return "redirect:/home";
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
					model.addAttribute("errormsg", "Username,Addhar number, Mobile number and mail id must be unique!!");
					return "thymeleaf/edit_user";
				}
				}
			else {
				model.addAttribute("passerror","Your password and Re-type password is not match!!! Please check");
			}
 
	  return "edit_user";
	  
	  }
	 
	 
	  
	  @RequestMapping("/userview")
		public String listAllDetails(Model m) {
		  	List<User> user=uservice.listAllUser();
			m.addAttribute("list",user);
			return "thymeleaf/user_view";
		}
	  
	  @RequestMapping("/useredit")
		public String editUser(Model m) {
		  
		  String username;
		  Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		  if(principal instanceof UserDetails) {
			  username=((UserDetails)principal).getUsername();
		  }else {
			  username=principal.toString();
		  }
		  
		  User user=uservice.getByUsername(username);
		  m.addAttribute("user",user);
			return "thymeleaf/edit_user";
		}
	
	@RequestMapping("/login")
	public String loginPage()
	{
		return "thymeleaf/login";
	}
	
	@RequestMapping("/logout-success")
	public String logoutPage(Model model)
	{
		model.addAttribute("logout","you have successfully logged out");
		return "thymeleaf/login";
	}
	
	@GetMapping("/searchUser")
	public String pidUserSubmit(@RequestParam(value="username") String username,Model m) {
		List<User> user=uservice.search(username);
		m.addAttribute("list",user);
	    return "thymeleaf/user_view";
	}
	
}
