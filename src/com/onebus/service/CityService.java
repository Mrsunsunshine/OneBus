package com.onebus.service;

import java.util.List;

import org.json.JSONArray;

import com.onebus.beans.Bus;
import com.onebus.beans.BusLine;
import com.onebus.beans.City;

public interface CityService {


	public void saveCity(City City);

	public void updateCity(City city);

	public void delete(City city);

	public List<City> find(int id);

	public List<City> findByName(String name);


}
