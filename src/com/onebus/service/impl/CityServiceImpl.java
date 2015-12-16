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
import com.onebus.beans.City;
import com.onebus.dao.BaseDAO;
import com.onebus.others.SendEmail;
import com.onebus.others.SendEmail.Email_Autherticator;
import com.onebus.service.BusLineService;
import com.onebus.service.BusService;
import com.onebus.service.CityService;

@Service("cityService")
@Transactional
public class CityServiceImpl implements CityService {

	@Resource
	private BaseDAO<City> baseDAO;

	public void delete(City City) {
		baseDAO.delete(City);
	}

	public void saveCity(City city) {
		baseDAO.save(city);
	}

	public List<City> find(int id) {
		String hql = "from com.onebus.beans.City where id = " + id;
		return baseDAO.find(hql);
	}

	@Override
	public void updateCity(City city) {
		baseDAO.update(city);

	}

	@Override
	public List<City> findByName(String name) {
		String hql = "from com.onebus.beans.City where name = ?";
		String[] para = { name };
		return baseDAO.find(hql, para);
	}

}
