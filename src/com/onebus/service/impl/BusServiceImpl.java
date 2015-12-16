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
import com.onebus.dao.BaseDAO;
import com.onebus.others.SendEmail;
import com.onebus.others.SendEmail.Email_Autherticator;
import com.onebus.service.BusLineService;
import com.onebus.service.BusService;

@Service("busService")
@Transactional
public class BusServiceImpl implements BusService {

	@Resource
	private BaseDAO<Bus> baseDAO;

	public void delete(Bus bus) {
		baseDAO.delete(bus);
	}

	public void saveBus(Bus bus) {
		baseDAO.save(bus);
	}

	public List<Bus> find(String plateNumber) {
		String hql = "from com.onebus.beans.Bus where plateNumber = ?";
		String[] para = { plateNumber };

		return baseDAO.find(hql, para);
	}

	@Override
	public void updateBus(Bus bus) {
		baseDAO.update(bus);

	}

	@Override
	public List<Bus> getAll(int companyId) {
		String hql = "from com.onebus.beans.Bus where companyId = " + companyId
				+ " order by plateNumber";
		return baseDAO.find(hql);
	}

	@Override
	public List<Bus> findById(int id) {
		String hql = "from com.onebus.beans.Bus where id = " + id;
		return baseDAO.find(hql);
	}

	@Override
	public List<Bus> findByBusLineId(int busLineId) {
		String hql = "from com.onebus.beans.Bus where busLineId = " + busLineId;
		return baseDAO.find(hql);
	}

	@Override
	public List<Bus> findAll() {
		String hql = "from com.onebus.beans.Bus";
		return baseDAO.find(hql);
	}

	@Override
	public List<Bus> findByCompanyId(int companyId) {
		String hql = "from com.onebus.beans.Bus where companyId = " + companyId;
		return baseDAO.find(hql);
	}

}
