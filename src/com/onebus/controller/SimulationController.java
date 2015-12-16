package com.onebus.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class SimulationController {

	@Resource
	private BusLineService busLineService;
	@Resource
	private BusService busService;

	// 转到实时地图界面
	@RequestMapping("/simulationMap")
	public String simulationMap(HttpServletRequest request,
			HttpServletResponse response) {

		Company company = (Company) request.getSession()
				.getAttribute("company");
		List<BusLine> busLines = busLineService
				.findByCompanyId(company.getId());

		request.setAttribute("busLines", busLines);

		return "simulationMap";

	}
	
	
	// 转到模拟线路界面
	@RequestMapping("/simulationLine")
	public String simulationLine(HttpServletRequest request,
			HttpServletResponse response) {

		Company company = (Company) request.getSession()
				.getAttribute("company");
		List<BusLine> busLines = busLineService
				.findByCompanyId(company.getId());

		request.setAttribute("busLines", busLines);

		return "simulationLine";

	}

	// 转到应急处理界面
	@RequestMapping("/emergencyTreatment")
	public String emergencyTreatment(HttpServletRequest request,
			HttpServletResponse response) {

		Company company = (Company) request.getSession()
				.getAttribute("company");
		List<BusLine> busLines = busLineService
				.findByCompanyId(company.getId());
		List<Bus> buss = busService.findByCompanyId(company.getId());

		request.setAttribute("busLines", busLines);
		request.setAttribute("buss", buss);

		return "emergencyTreatment";

	}

	// 处理应急事件
	@RequestMapping("/emergencyDeal")
	public String emergencyDeal(HttpServletRequest request,
			HttpServletResponse response) {

		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		request.setAttribute("now", dateString);

		return "main";

	}

}
