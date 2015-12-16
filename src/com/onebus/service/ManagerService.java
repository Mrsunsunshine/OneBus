package com.onebus.service;

import java.util.List;

import com.onebus.beans.Manager;

public interface ManagerService {

	public void saveManager(Manager manager);
	
	public void updateManager(Manager manager);

	public Manager login(Manager manager);

	public void delete(Manager manager);

	public List<Manager> find(String username);
	
	public List<Manager> findByEmail(String email);
	
	public List<Manager> findByPhone(String phone);

	public void send(String email, String content, String mailSubject);

	public List<Manager> getAll(int companyId);

	public List<Manager> findByNumber(String number);



}
