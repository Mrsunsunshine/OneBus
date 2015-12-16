package com.onebus.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.mapping.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.onebus.beans.City;
import com.onebus.beans.GoodsScore;
import com.onebus.beans.User;
import com.onebus.others.Avatar;
import com.onebus.others.PS;
import com.onebus.others.SecurityCode;
import com.onebus.others.SendEmail;
import com.onebus.service.CityService;
import com.onebus.service.GoodsScoreService;
import com.onebus.service.UserService;

@Controller
@RequestMapping("/android")
public class GoodsScoreController {
	@Resource
	private UserService userService;

	@Resource
	private GoodsScoreService goodsScoreService;

	@Resource
	private CityService cityService;

	// 获取积分
	@RequestMapping(value = "/getScore", method = RequestMethod.POST)
	public void getScore(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			System.out.println("/getScore");
			String params = request.getParameter("getScore");
			JSONArray jsonArray1 = new JSONArray(params);
			JSONObject jsonObject1 = (JSONObject) jsonArray1.get(0);

			String phone = jsonObject1.optString("phone");
			System.out.println(phone);

			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject2 = new JSONObject();

			List<User> users = userService.findByPhone(phone);
			int score = users.get(0).getScore();

			jsonObject2.put("status", "success");
			jsonObject2.put("score", score);

			System.out.println("ok");

			jsonArray.put(jsonObject2);

			// 解决中文乱码问题
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			response.getWriter().println(jsonArray.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取所有商品
	@RequestMapping(value = "/getAllGoods", method = RequestMethod.POST)
	public void getAllGoods(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			System.out.println("getAllGoods");
			JSONArray jsonArray2 = new JSONArray();

			List<GoodsScore> goodsScores = goodsScoreService.getAllGoodsScore();
			System.out.println(goodsScores);

			Avatar avatar = new Avatar();
			for (GoodsScore goodsScore : goodsScores) {
				JSONObject jsonObject2 = new JSONObject();

				jsonObject2.put("goodsName", goodsScore.getGoodsName());

				String goodsPicture = avatar.pathToImage(goodsScore
						.getGoodsPicture());
				jsonObject2.put("goodsPicture", goodsPicture);
				jsonObject2.put("score", goodsScore.getScore());

				jsonArray2.put(jsonObject2);
			}

			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			response.getWriter().println(jsonArray2.toString());

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// 更改积分
	@RequestMapping(value = "/setScore", method = RequestMethod.POST)
	public void setScore(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			System.out.println("/setScore");
			String params = request.getParameter("setScore");
			JSONArray jsonArray1 = new JSONArray(params);
			JSONObject jsonObject1 = (JSONObject) jsonArray1.get(0);

			String phone = jsonObject1.optString("phone");
			int newScore = jsonObject1.optInt("score");

			JSONArray jsonArray2 = new JSONArray();
			JSONObject jsonObject2 = new JSONObject();

			List<User> users = userService.findByPhone(phone);
			User user = users.get(0);

			user.setScore(newScore + user.getScore());
			System.out.println(user.getScore());
			userService.updateUser(user);

			jsonObject2.put("status", "success");

			jsonArray2.put(jsonObject2);

			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			response.getWriter().println(jsonArray2.toString());

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
