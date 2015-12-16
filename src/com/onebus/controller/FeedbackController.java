package com.onebus.controller;

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
public class FeedbackController {
	@Resource
	private FeedbackService feedbackService;

	@Resource
	private BusService busService;

	// 提交feedback
	@RequestMapping(value = "/feedback", method = RequestMethod.POST)
	public void feedback(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			System.out.println("feedback");
			String params = request.getParameter("feedback");
			JSONArray jsonArray1 = new JSONArray(params);
			JSONObject jsonObject1 = (JSONObject) jsonArray1.get(0);

			String userPhone = jsonObject1.optString("userPhone");
			String plateNumber = jsonObject1.optString("busnumber");
			String feedbackType = jsonObject1.optString("feedbackType");// 意见||投诉
			String content = jsonObject1.optString("content");
			Timestamp feedbackTime = Timestamp.valueOf(jsonObject1
					.optString("feedbackTime"));
			String contactWay = jsonObject1.optString("contactWay");

			System.out.println(feedbackTime + feedbackType + plateNumber
					+ userPhone);

			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject2 = new JSONObject();

			boolean replied = false;
			Bus bus;
			if(feedbackType.equals("意见"))
			{
				bus = busService.findAll().get(0);
				
			}
			else
			{
				bus = busService.find(plateNumber).get(0);
			}
			
			Feedback fd = new Feedback(userPhone, bus, feedbackType, content,
					feedbackTime, replied, contactWay);

			feedbackService.saveFeedback(fd);

			jsonObject2.put("status", "success");

			jsonArray.put(jsonObject2);

			// 解决中文乱码问题
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			response.getWriter().println(jsonArray.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
