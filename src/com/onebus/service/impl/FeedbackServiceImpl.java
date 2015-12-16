package com.onebus.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onebus.beans.Bus;
import com.onebus.beans.BusLine;
import com.onebus.beans.Feedback;
import com.onebus.dao.BaseDAO;
import com.onebus.others.SendEmail;
import com.onebus.others.SendEmail.Email_Autherticator;
import com.onebus.service.BusLineService;
import com.onebus.service.BusService;
import com.onebus.service.FeedbackService;

@Service("feedbackService")
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

	@Resource
	private BaseDAO<Feedback> baseDAO;

	public void delete(Feedback feedback) {
		baseDAO.delete(feedback);
	}

	public void saveFeedback(Feedback feedback) {
		baseDAO.save(feedback);
	}

	public List<Feedback> find(int id) {
		String hql = "from com.onebus.beans.Feedback where id = "+id;
		return baseDAO.find(hql);
	}

	@Override
	public void updateFeedback(Feedback feedback) {
		baseDAO.update(feedback);

	}

	@Override
	public List<Feedback> findByBusId(int busId) {
		String hql = "from com.onebus.beans.Feedback where busId = "+busId;
		return baseDAO.find(hql);
	}

	@Override
	public List<Feedback> findAll() {
		String hql = "from com.onebus.beans.Feedback";
		return baseDAO.find(hql);
	}

}
