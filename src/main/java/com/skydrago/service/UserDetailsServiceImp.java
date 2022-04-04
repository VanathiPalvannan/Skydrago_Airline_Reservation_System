package com.skydrago.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.skydrago.config.ProfileDetails;
import com.skydrago.entity.User;
import com.skydrago.repository.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	
	@Autowired
	UserRepository repo;
	
	BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setrPassword(passwordEncoder.encode(user.getrPassword()));
		repo.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=repo.findByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException("user 403");
		}
		return new ProfileDetails(user);
	}
	
	public List<User> listAllUser(){
		return repo.listUserOnly();
	}
	
	public List<User> search(String username){
		List<User> users=new ArrayList<>();
		users.add(repo.findByUserName(username));
		return users;   
	}
	
	public User getById(int id) {
		return repo.getById(id);
	}
	
	public User get(int id) {
		return repo.findById(id).get();
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
	
	public User getByUsername(String username) {
		return repo.findByUserName(username);
	}
	

}
