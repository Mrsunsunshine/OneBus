package com.onebus.service;

import java.util.List;

import org.json.JSONObject;

import com.onebus.beans.BusLine;

public interface BusLineService {

	public void saveBusLine(BusLine busLine);

	public void updateBusLine(BusLine busLine);

	public void delete(BusLine busLine);

	public List<BusLine> findByCompanyId(int companyId);

	public List<BusLine> findByLineNumber(String lineNumber);

	public List<BusLine> findById(int id);

}
