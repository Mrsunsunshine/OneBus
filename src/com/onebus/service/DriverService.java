package com.onebus.service;

import java.util.List;

import com.onebus.beans.Driver;

public interface DriverService {

	public void saveDriver(Driver driver);
	
	public void updateDriver(Driver driver);

	public void delete(Driver driver);

	public List<Driver> find(String number);
	
	public List<Driver> getAll(int companyId);

}
