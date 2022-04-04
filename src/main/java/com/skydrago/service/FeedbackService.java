package com.skydrago.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skydrago.entity.FeedBack;
import com.skydrago.repository.FeedbackRepository;

@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackRepository feedbackrepo;
	
	public List<FeedBack> getAllFeedbacks() {
		return feedbackrepo.findAll();
	}
	public void save(FeedBack feedback) {
		feedbackrepo.save(feedback);
	}

}
