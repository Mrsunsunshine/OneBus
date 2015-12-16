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

import com.onebus.beans.Company;
import com.onebus.beans.Manager;
import com.onebus.dao.BaseDAO;
import com.onebus.others.SendEmail;
import com.onebus.others.SendEmail.Email_Autherticator;
import com.onebus.service.CompanyService;
import com.onebus.service.ManagerService;

@Service("companyService")
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Resource
	private BaseDAO<Company> baseDAO;

	public void delete(Company company) {
		baseDAO.delete(company);
	}

	public void saveCompany(Company company) {
		baseDAO.save(company);
	}

	public List<Company> find(String username) {
		String hql = "from com.onebus.beans.Company where username = ?";
		String[] para = { username };

		return baseDAO.find(hql, para);
	}
	
	@Override
	public void updateCompany(Company company) {
		baseDAO.update(company);

	}

	@Override
	public List<Company> findById(int id) {
		String hql = "from com.onebus.beans.Company where id = "+id;
		return baseDAO.find(hql);
	}

}
