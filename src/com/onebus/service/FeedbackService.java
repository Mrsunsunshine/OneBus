package com.onebus.service;

import java.util.List;

import com.onebus.beans.Bus;
import com.onebus.beans.BusLine;
import com.onebus.beans.Feedback;

public interface FeedbackService {

	public void saveFeedback(Feedback Feedback);

	public void updateFeedback(Feedback Feedback);

	public void delete(Feedback feedback);

	public List<Feedback> find(int id);

	public List<Feedback> findByBusId(int busId);
	
	public List<Feedback> findAll();

}
