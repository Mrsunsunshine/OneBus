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

import com.onebus.beans.Manager;
import com.onebus.dao.BaseDAO;
import com.onebus.others.SendEmail;
import com.onebus.others.SendEmail.Email_Autherticator;
import com.onebus.service.ManagerService;

@Service("managerService")
@Transactional
public class ManagerServiceImpl implements ManagerService {

	@Resource
	private BaseDAO<Manager> baseDAO;

	public void delete(Manager manager) {
		baseDAO.delete(manager);
	}

	public void saveManager(Manager manager) {
		baseDAO.save(manager);
	}

	public Manager login(Manager manager) {
		return null;
	}

	public List<Manager> find(String username) {
		String hql = "from com.onebus.beans.Manager where username = ?";
		String[] para = { username };
		
		return baseDAO.find(hql, para);
	}
	
	public List<Manager> findByEmail(String email) {
		String hql = "from com.onebus.beans.Manager where email = ?";
		String[] para = { email };
		return baseDAO.find(hql, para);
	}
	
	public List<Manager> findByPhone(String phone) {
		String hql = "from com.onebus.beans.Manager where phone = ?";
		String[] para = { phone };
		return baseDAO.find(hql, para);
	}

	@Override
	public void send(String email, String content, String mailSubject) {
		SendEmail sendEmail = new SendEmail();
		try {
			sendEmail.send(email, content, mailSubject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void updateManager(Manager manager) {
		 baseDAO.update(manager);
		
	}

	@Override
	public List<Manager> getAll(int companyId) {
		String hql = "from com.onebus.beans.Manager where companyId = "+companyId +"order by number";
		return baseDAO.find(hql);
	}

	@Override
	public List<Manager> findByNumber(String number) {
		String hql = "from com.onebus.beans.Manager where number = ?";
		String[] para = { number };
		return baseDAO.find(hql, para);
	}

}
