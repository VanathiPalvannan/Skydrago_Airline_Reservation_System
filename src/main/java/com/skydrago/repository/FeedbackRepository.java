package com.skydrago.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skydrago.entity.FeedBack;

public interface FeedbackRepository extends JpaRepository<FeedBack, Integer> {

}