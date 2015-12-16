package com.onebus.service;

import java.util.List;

import com.onebus.beans.Bus;
import com.onebus.beans.BusLine;

public interface BusService {

	public void saveBus(Bus bus);

	public void updateBus(Bus bus);

	public void delete(Bus bus);

	public List<Bus> find(String plateNumber);

	public List<Bus> getAll(int companyId);

	public List<Bus> findById(int id);

	public List<Bus> findByBusLineId(int busLineId);
	
	public List<Bus> findAll();

	public List<Bus> findByCompanyId(int companyId);

}
