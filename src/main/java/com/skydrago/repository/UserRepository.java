package com.skydrago.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skydrago.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	User findByUserName(String username);
	
	@Query(value="select * from user where role='USER'",nativeQuery=true)
	List<User> listUserOnly();
}
