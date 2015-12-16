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

import com.onebus.beans.BusLine;
import com.onebus.dao.BaseDAO;
import com.onebus.others.SendEmail;
import com.onebus.others.SendEmail.Email_Autherticator;
import com.onebus.service.BusLineService;

@Service("busLineService")
@Transactional
public class BusLineServiceImpl implements BusLineService {

	@Resource
	private BaseDAO<BusLine> baseDAO;

	public void delete(BusLine busLine) {
		baseDAO.delete(busLine);
	}

	public void saveBusLine(BusLine busLine) {
		baseDAO.save(busLine);
	}
	
	public List<BusLine> findByCompanyId(int companyId) {
		String hql = "from com.onebus.beans.BusLine where companyId = "+companyId;

		return baseDAO.find(hql);
	}

	public List<BusLine> findByLineNumber(String lineNumber) {
		String hql = "from com.onebus.beans.BusLine where lineNumber = ?";
		String[] para = { lineNumber };

		return baseDAO.find(hql, para);
	}

	@Override
	public void updateBusLine(BusLine busLine) {
		baseDAO.update(busLine);

	}

	@Override
	public List<BusLine> findById(int id) {
		String hql = "from com.onebus.beans.BusLine where id = "+id;

		return baseDAO.find(hql);
	}



}
