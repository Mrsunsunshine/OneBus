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

import com.onebus.beans.Driver;
import com.onebus.dao.BaseDAO;
import com.onebus.service.DriverService;


@Service("driverService")
@Transactional
public class DriverServiceImpl implements DriverService {

	@Resource
	private BaseDAO<Driver> baseDAO;

	public void delete(Driver driver) {
		baseDAO.delete(driver);
	}

	public void saveDriver(Driver driver) {
		baseDAO.save(driver);
	}

	public List<Driver> find(String number) {
		String hql = "from com.onebus.beans.Driver where number = ?";
		String[] para = {number};
		return baseDAO.find(hql, para);
	}

	@Override
	public void updateDriver(Driver driver) {
		baseDAO.update(driver);
		
	}

	@Override
	public List<Driver> getAll(int companyId) {
		String hql = "from com.onebus.beans.Driver where companyId = "+companyId+" order by number";
		return baseDAO.find(hql);
	}
}
