package com.onebus.controller;

import java.util.ArrayList;
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

@Controller
@RequestMapping("/main")
public class VideoController {

	@Resource
	private BusService busService;

	// 转到搜寻video界面
	@RequestMapping("/searchVideo")
	public String searchVideo(HttpServletRequest request,
			HttpServletResponse response) {

		Company company = (Company) request.getSession()
				.getAttribute("company");
		List<Bus> buss = busService.getAll(company.getId());
		List<Bus> buss2 = new ArrayList<Bus>();

		// 把已出发的车放入buss2 然后显示到界面
		for (Bus bus : buss) {
			if (bus.isStarted())
				buss2.add(bus);
		}
		request.setAttribute("buss", buss2);

		return "searchVideo";

	}

	// 修改公交信息
	@RequestMapping("/video")
	public String submitEditBus(HttpServletRequest request,
			HttpServletResponse response) {

		int busId = Integer.parseInt(request.getParameter("busId"));
		System.out.println("查看视频的车号：" + busId);
		Bus bus = busService.findById(busId).get(0);
		request.setAttribute("bus", bus);
		return "video";
	}

}
