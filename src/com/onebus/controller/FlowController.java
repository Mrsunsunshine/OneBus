package com.onebus.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.onebus.service.BusLineService;
import com.onebus.service.BusService;


@Controller
@RequestMapping("/main")
public class FlowController {

	@Resource
	private BusLineService busLineService;

	@Resource
	private BusService busService;

	// 转到选择路线界面
	@RequestMapping("/busLineFlow")
	public String searchVideo(HttpServletRequest request,
			HttpServletResponse response) {

		Company company = (Company) request.getSession()
				.getAttribute("company");
		List<BusLine> busLines = busLineService
				.findByCompanyId(company.getId());

		request.setAttribute("busLines", busLines);
		return "busLineFlow";
	}

	// 如何为所有路线（-1）跳转至time，否者bus
	@RequestMapping("/nextFlow")
	public String nextFlow(HttpServletRequest request,
			HttpServletResponse response) {

		int busLineId = Integer.parseInt(request.getParameter("busLineId"));

		if (busLineId == -1)// 所有路线
		{
			return "timeFlow";
		}

		int busLineFlag = busLineId;
		request.getSession().setAttribute("busLineFlag", busLineFlag);

		List<Bus> buss = busService.findByBusLineId(busLineId);
		request.setAttribute("buss", buss);

		return "busFlow";
	}

	// 如何为所有路线（-1）跳转至time，否者bus
	@RequestMapping("/timeFlow")
	public String timeFlow(HttpServletRequest request,
			HttpServletResponse response) {

		int busId = Integer.parseInt(request.getParameter("busId"));
		int busFlag = busId;
		request.getSession().setAttribute("busFlag", busFlag);
		
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String today = df.format(date);
		request.setAttribute("today", today);
		return "timeFlow";
	}

	// 转到客流量
	@RequestMapping("/flow")
	public String flow(HttpServletRequest request, HttpServletResponse response) {

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		System.out.println(startTime + "-" + endTime);

		int busLineFlag = (int) request.getSession()
				.getAttribute("busLineFlag");
		int busFlag = (int) request.getSession().getAttribute("busFlag");

		System.out.println("busLineFlag:" + busLineFlag);
		String lineNumber = "所有线路";
		if (busLineFlag != -1) {
			BusLine bl = busLineService.findById(busLineFlag).get(0);
			lineNumber = bl.getLineNumber();
		}

		String plateNumber = "所有车辆";
		if (busLineFlag != -1) {
			Bus bus = busService.findById(busFlag).get(0);
			plateNumber = bus.getPlateNumber();
		}

		String periodTime = startTime + "至" + endTime;

		// biaoji
		int userNumber = 54614;

		request.setAttribute("lineNumber", lineNumber);
		request.setAttribute("plateNumber", plateNumber);
		request.setAttribute("periodTime", periodTime);
		request.setAttribute("userNumber", userNumber);

		return "flow";
	}

}
