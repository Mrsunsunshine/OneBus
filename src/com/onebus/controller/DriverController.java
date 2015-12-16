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

import com.onebus.beans.Company;
import com.onebus.beans.Driver;
import com.onebus.beans.Manager;
import com.onebus.others.SendEmail;
import com.onebus.service.DriverService;
import com.onebus.service.ManagerService;


@Controller
@RequestMapping("/main")
public class DriverController {
	@Resource
	private DriverService driverService;
	
	@Resource
	private ManagerService managerService;

	// 转到司机信息界面
	@RequestMapping("/driverInfor")
	public String driverInfor(HttpServletRequest request,
			HttpServletResponse response) {
			
		Company company = (Company) request.getSession().getAttribute("company");
		List<Driver> drivers = driverService.getAll(company.getId());
		
		request.setAttribute("drivers", drivers);

		return "driverInfor";

	}

	// 删除制定司机
	@RequestMapping("/deleteDriver")
	public String deleteDriver(HttpServletRequest request,
			HttpServletResponse response) {

		String number = request.getParameter("number");
		Driver driver = driverService.find(number).get(0);
		driverService.delete(driver);
		System.out.println(number);

	    Company company = (Company) request.getSession().getAttribute("company");
		List<Driver> drivers = driverService.getAll(company.getId());
		
		request.setAttribute("drivers", drivers);

		return "driverInfor";

	}

	// 转到添加司机界面
	@RequestMapping("/addDriver")
	public String addDriver(HttpServletRequest request,
			HttpServletResponse response) {

		return "addDriver";

	}

	// 添加司机
	@RequestMapping("/submitDriver")
	public String submitDriver(HttpServletRequest request,
			HttpServletResponse response) {
		String number = request.getParameter("number");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String sex = request.getParameter("sex");

		if (driverService.find(number).size() > 0) {
			request.setAttribute("sD", "numberExist");

			return "addDriver";

		}
		
		Company company = (Company) request.getSession().getAttribute("company");
	
		Driver driver = new Driver(number, name, age, sex, company);

		driverService.saveDriver(driver);
		System.out.println(number + ":" + name + ":" + age + ":" + sex + ":"
				+ company);

		
		List<Driver> drivers = driverService.getAll(company.getId());
		request.setAttribute("drivers", drivers);

		return "driverInfor";

	}

	// 转到修改司机信息界面
	@RequestMapping("/editDriver")
	public String editDriver(HttpServletRequest request,
			HttpServletResponse response) {
		
		String number = request.getParameter("number");
		Driver driver = driverService.find(number).get(0);
		
		request.setAttribute("driver", driver);
		return "editDriver";

	}

	//修改司机信息
	@RequestMapping("/submitEditDriver")
	public String submitEditDriver(HttpServletRequest request,
			HttpServletResponse response) {
		
		String number = request.getParameter("number");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String sex = request.getParameter("sex");
		
		Driver driver = driverService.find(number).get(0);
		driver.setName(name);
		driver.setAge(age);
		driver.setSex(sex);
		
		driverService.updateDriver(driver);
		
		request.setAttribute("sED", "editSuccess");
	
		Company company = (Company) request.getSession().getAttribute("company");
		List<Driver> drivers = driverService.getAll(company.getId());
		
		request.setAttribute("drivers", drivers);
		return "driverInfor";

	}

}
