package com.onebus.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.onebus.beans.Manager;
import com.onebus.others.SendEmail;
import com.onebus.service.BusLineService;
import com.onebus.service.BusService;
import com.onebus.service.DriverService;
import com.onebus.service.FeedbackService;
import com.onebus.service.ManagerService;

@Controller
@RequestMapping("/main")
public class SmartScheduleController {

	@Resource
	private BusLineService busLineService;

	// 转到智能调度界面
	@RequestMapping("/smartSchedule")
	public String smartSchedule(HttpServletRequest request,
			HttpServletResponse response) {

		Company company = (Company) request.getSession()
				.getAttribute("company");
		List<BusLine> busLines = busLineService
				.findByCompanyId(company.getId());

		request.setAttribute("busLines", busLines);

		return "smartSchedule";

	}

}
