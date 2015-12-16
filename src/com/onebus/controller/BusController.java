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
import com.onebus.service.ManagerService;

//@RestController //通过此就访问不了jsp
@Controller
@RequestMapping("/main")
public class BusController {
	@Resource
	private BusService busService;

	@Resource
	private BusLineService busLineService;

	@Resource
	private ManagerService managerService;

	// 转到车辆信息界面
	@RequestMapping("/busInfor")
	public String busInfor(HttpServletRequest request,
			HttpServletResponse response) {

		Company company = (Company) request.getSession()
				.getAttribute("company");
		List<Bus> buss = busService.getAll(company.getId());

		request.setAttribute("buss", buss);

		return "busInfor";

	}

	// 删除车辆
	@RequestMapping("/deleteBus")
	public String deleteBus(HttpServletRequest request,
			HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("busId"));
		Bus bus = busService.findById(id).get(0);
		busService.delete(bus);

		Company company = (Company) request.getSession()
				.getAttribute("company");
		List<Bus> buss = busService.getAll(company.getId());

		request.setAttribute("buss", buss);

		return "busInfor";

	}

	// 转到添加车辆界面
	@RequestMapping("/addBus")
	public String addBus(HttpServletRequest request,
			HttpServletResponse response) {

		Company company = (Company) request.getSession()
				.getAttribute("company");
		List<BusLine> busLines = busLineService
				.findByCompanyId(company.getId());
		request.setAttribute("busLines", busLines);
		return "addBus";

	}

	// 添加车辆
	@RequestMapping("/submitBus")
	public String submitBus(HttpServletRequest request,
			HttpServletResponse response) {
		String plateNumber = request.getParameter("plateNumber");
		String busType = request.getParameter("busType");
		String lineNumber = request.getParameter("lineNumber");

		Company company = (Company) request.getSession()
				.getAttribute("company");
		if (busService.find(plateNumber).size() > 0) {

			request.setAttribute("sB", "plateNumberExist");
			List<BusLine> busLines = busLineService.findByCompanyId(company
					.getId());
			request.setAttribute("busLines", busLines);
			return "addBus";

		}
		
		System.out.println(plateNumber + ":" + busType + ":" + lineNumber);

		BusLine busLine = busLineService.findByLineNumber(lineNumber).get(0);
		double price = 1.0;
		if( busType.indexOf("空调")>=0)
		{
			 price = 2.0;
		}
		Bus bus = new Bus(busLine, company, plateNumber, busType, price);

		busService.saveBus(bus);

		List<Bus> buss = busService.getAll(company.getId());
		request.setAttribute("buss", buss);

		return "busInfor";

	}

	// 转到修改车辆信息界面
	@RequestMapping("/editBus")
	public String editBus(HttpServletRequest request,
			HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("busId"));
		System.out.println(id);
		Bus bus = busService.findById(id).get(0);
		request.setAttribute("bus", bus);

		Company company = (Company) request.getSession()
				.getAttribute("company");
		List<BusLine> busLines = busLineService
				.findByCompanyId(company.getId());
		request.setAttribute("busLines", busLines);
		return "editBus";

	}

	// 修改公交信息
	@RequestMapping("/submitEditBus")
	public String submitEditBus(HttpServletRequest request,
			HttpServletResponse response) {

		String plateNumber = request.getParameter("plateNumber");
		String busType = request.getParameter("busType");
		String lineNumber = request.getParameter("lineNumber");

		Bus bus = busService.find(plateNumber).get(0);
		bus.setBusType(busType);
		BusLine busLine = busLineService.findByLineNumber(lineNumber).get(0);
		bus.setBusLine(busLine);
		busService.updateBus(bus);
		request.setAttribute("sEB", "editSuccess");
		Company company = (Company) request.getSession()
				.getAttribute("company");
		List<Bus> buss = busService.getAll(company.getId());

		request.setAttribute("buss", buss);
		return "busInfor";

	}

}
