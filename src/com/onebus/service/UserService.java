package com.onebus.service;

import java.util.List;

import org.json.JSONObject;

import com.onebus.beans.User;

public interface UserService {

	public void saveUser(User user);

	public User login(User user);

	public void delete(User user);

	public List<User> find(String username);

	public List<User> findByPhone(String phone);

	public void updateUser(User user);

}
