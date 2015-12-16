package com.onebus.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onebus.beans.Bus;
import com.onebus.beans.BusLine;
import com.onebus.beans.Company;
import com.onebus.beans.Driver;
import com.onebus.beans.Feedback;
import com.onebus.beans.Manager;
import com.onebus.beans.User;
import com.onebus.others.Avatar;
import com.onebus.others.SendEmail;
import com.onebus.service.BusLineService;
import com.onebus.service.BusService;
import com.onebus.service.DriverService;
import com.onebus.service.FeedbackService;
import com.onebus.service.ManagerService;

@Controller
@RequestMapping("/android")
public class BusPriceController {

	@Resource
	private BusService busService;

	// 获取车费
	@RequestMapping(value = "/getBusPrice", method = RequestMethod.POST)
	public void getBusPrice(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("getBusPrice");

		String plateNumber = request.getParameter("plateNumber");

		System.out.println("getBusPrice:" + plateNumber);
		List<Bus> buss = busService.find(plateNumber);
		double busPrice = 0.0;
		if (buss.size() > 0) {
			Bus bus = buss.get(0);
			busPrice = bus.getPrice();
		}
		
		System.out.println(busPrice);
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		try {
			response.getWriter().println(busPrice);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
