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
import com.onebus.beans.Feedback;
import com.onebus.beans.Manager;
import com.onebus.others.SendEmail;
import com.onebus.service.BusLineService;
import com.onebus.service.BusService;
import com.onebus.service.DriverService;
import com.onebus.service.FeedbackService;
import com.onebus.service.ManagerService;
import com.onebus.service.UserService;


@Controller
@RequestMapping("/main")
public class ShowFeedbackController {
	@Resource
	private FeedbackService feedbackService;
	@Resource
	private UserService userService;

	// 转到投诉界面
	@RequestMapping("/userComplaint")
	public String userComplaint(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("/userComplaint");
		Company company = (Company) request.getSession()
				.getAttribute("company");

		List<Feedback> feedbacks = feedbackService.findAll();
		List<Feedback> doFeedbacks = new ArrayList<Feedback>();
		List<Feedback> noFeedbacks = new ArrayList<Feedback>();
		for (Feedback feedback : feedbacks) {
			if (feedback.getFeedbackType().equals("投诉")
					&& feedback.getBus().getCompany().getId() == company
							.getId()) {

				if (feedback.getUserPhone() == null) {
					feedback.setUserPhone("匿名");
				} else {
					feedback.setUserPhone(userService
							.findByPhone(feedback.getUserPhone()).get(0)
							.getUsername());
				}
				if (feedback.isReplied() == true) {
					doFeedbacks.add(feedback);
				} else {
					noFeedbacks.add(feedback);
				}
			}

		}
		request.setAttribute("doFeedbacks", doFeedbacks);
		request.setAttribute("noFeedbacks", noFeedbacks);
		return "userComplaint";

	}

	// 转到详细投诉界面
	@RequestMapping("/userComplaintInfor")
	public String userComplaintInfor(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));

		Feedback feedback = feedbackService.find(id).get(0);

		if (feedback.getUserPhone() == null) {
			feedback.setUserPhone("匿名");
		} else {
			feedback.setUserPhone(userService
					.findByPhone(feedback.getUserPhone()).get(0).getUsername());
		}

		request.setAttribute("feedback", feedback);
		return "userComplaintInfor";

	}

	// 转到已处理的详细投诉界面
	@RequestMapping("/userComplaintInforDo")
	public String userComplaintInforDo(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));

		Feedback feedback = feedbackService.find(id).get(0);

		if (feedback.getUserPhone() == null) {
			feedback.setUserPhone("匿名");
		} else {
			feedback.setUserPhone(userService
					.findByPhone(feedback.getUserPhone()).get(0).getUsername());
		}

		request.setAttribute("feedback", feedback);
		return "userComplaintInforDo";

	}

	// 投诉处理
	@RequestMapping("/userComplaintDeal")
	public String userComplaintDeal(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));

		Feedback feedback = feedbackService.find(id).get(0);

		feedback.setReplied(true);

		feedbackService.updateFeedback(feedback);

		return userComplaint(request, response);

	}

	// 转到意见界面
	@RequestMapping("/userRecommendation")
	public String userRecommendation(HttpServletRequest request,
			HttpServletResponse response) {

		List<Feedback> feedbacks = feedbackService.findAll();
		List<Feedback> doFeedbacks = new ArrayList<Feedback>();
		List<Feedback> noFeedbacks = new ArrayList<Feedback>();
		for (Feedback feedback : feedbacks) {
			if (feedback.getFeedbackType().equals("意见")) {

				if (feedback.getUserPhone() == null) {
					feedback.setUserPhone("匿名");
				} else {
					feedback.setUserPhone(userService
							.findByPhone(feedback.getUserPhone()).get(0)
							.getUsername());
				}

				if (feedback.isReplied() == true)
					doFeedbacks.add(feedback);
				else
					noFeedbacks.add(feedback);
			}
		}
		request.setAttribute("doFeedbacks", doFeedbacks);
		request.setAttribute("noFeedbacks", noFeedbacks);
		return "userRecommendation";

	}

	// 转到详细意见界面
	@RequestMapping("/userRecommendationInfor")
	public String userRecommendationInfor(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));

		Feedback feedback = feedbackService.find(id).get(0);

		if (feedback.getUserPhone() == null) {
			feedback.setUserPhone("匿名");
		} else {
			feedback.setUserPhone(userService
					.findByPhone(feedback.getUserPhone()).get(0).getUsername());
		}

		request.setAttribute("feedback", feedback);
		return "userRecommendationInfor";

	}

	// 转到已处理的详细意见界面
	@RequestMapping("/userRecommendationInforDo")
	public String userRecommendationInforDo(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));

		Feedback feedback = feedbackService.find(id).get(0);

		if (feedback.getUserPhone() == null) {
			feedback.setUserPhone("匿名");
		} else {
			feedback.setUserPhone(userService
					.findByPhone(feedback.getUserPhone()).get(0).getUsername());
		}

		request.setAttribute("feedback", feedback);
		return "userRecommendationInforDo";

	}

	// 投诉意见
	@RequestMapping("/userRecommendationDeal")
	public String userRecommendationDeal(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));

		Feedback feedback = feedbackService.find(id).get(0);

		feedback.setReplied(true);

		feedbackService.updateFeedback(feedback);

		return userRecommendation(request, response);

	}

}
