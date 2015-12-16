package com.onebus.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onebus.beans.User;
import com.onebus.dao.BaseDAO;
import com.onebus.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private BaseDAO<User> baseDAO;

	public void delete(User user) {
		baseDAO.delete(user);
	}

	public void saveUser(User user) {
		baseDAO.save(user);
	}

	public User login(User user) {
		return null;
	}

	public List<User> find(String username) {
		String hql = "from com.onebus.beans.User where username = ?";
		String[] para = { username };
		return baseDAO.find(hql, para);
	}

	@Override
	public List<User> findByPhone(String phone) {
		String hql = "from com.onebus.beans.User where phone = ?";
		String[] para = { phone };
		return baseDAO.find(hql, para);
	}

	@Override
	public void updateUser(User user) {
		baseDAO.update(user);		
	}
}
