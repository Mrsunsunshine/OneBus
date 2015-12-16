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
import com.onebus.beans.User;
import com.onebus.others.Avatar;
import com.onebus.others.PS;
import com.onebus.others.SecurityCode;
import com.onebus.others.SendEmail;
import com.onebus.service.CityService;
import com.onebus.service.UserService;

@Controller
@RequestMapping("/android")
public class UserController {
	@Resource
	private UserService userService;

	@Resource
	private CityService cityService;

	private Queue<PS> queue = new LinkedList<PS>();
	private HashMap<String, String> hashMap = new HashMap<String, String>();

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response) {
		try {

			String params = request.getParameter("login");
			JSONArray jsonArray1 = new JSONArray(params);
			JSONObject jsonObject1 = (JSONObject) jsonArray1.get(0);

			String phone = jsonObject1.optString("userphone");
			String password = jsonObject1.optString("userpassword");
			System.out.println(phone + password);

			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject2 = new JSONObject();

			List<User> users = userService.findByPhone(phone);
			if (users.size() == 0) {
				jsonObject2.put("status", "noUser");
				System.out.println("noUser");
			} else {
				User user = users.get(0);
				if (!user.getPassword().equals(password)) {
					jsonObject2.put("status", "wrongPassword");
					System.out.println("密码错误");
				} else {
					String path = user.getAvatar();

					Avatar ava = new Avatar();

					jsonObject2.put("status", "success");
					jsonObject2.put("sex", user.getSex());
					jsonObject2.put("avatar", ava.pathToImage(path));
					jsonObject2.put("phone", user.getPhone());
					jsonObject2.put("username", user.getUsername());

					System.out.println("ok");
				}
			}
			jsonArray.put(jsonObject2);

			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			response.getWriter().println(jsonArray.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
	public void setUser(HttpServletRequest request, HttpServletResponse response) {
		try {

			String params = request.getParameter("modify");
			JSONArray jsonArray1 = new JSONArray(params);
			JSONObject jsonObject1 = (JSONObject) jsonArray1.get(0);

			int sex = Integer.parseInt(jsonObject1.optString("sex"));
			String avatar = jsonObject1.optString("avatar");
			String phone = jsonObject1.optString("phone");
			String username = jsonObject1.optString("username");

			System.out.println(sex + ":" + phone + ":" + username + ":");
			JSONArray jsonArray2 = new JSONArray();
			JSONObject jsonObject2 = new JSONObject();

			List<User> users = userService.findByPhone(phone);

			User user = users.get(0);
			user.setSex(sex);
			user.setPhone(phone);
			user.setUsername(username);
			String imgFilePath = "C:\\avatar\\" + user.getId() + ".jpg";
			Avatar ava = new Avatar();
			if (!ava.GenerateImage(avatar, imgFilePath)) {
				jsonObject2.put("status", "avatarNull");
			}
			user.setAvatar(imgFilePath);

			userService.updateUser(user);

			jsonObject2.put("status", "success");
			jsonArray2.put(jsonObject2);

			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			response.getWriter().println(jsonArray2.toString());

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// 更改密码
	@RequestMapping(value = "/setUserPassword", method = RequestMethod.POST)
	public void setUserPassword(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String params = request.getParameter("setUserPassword");
			JSONArray jsonArray1 = new JSONArray(params);
			JSONObject jsonObject1 = (JSONObject) jsonArray1.get(0);

			String phone = jsonObject1.optString("phone");
			String password = jsonObject1.optString("password");
			String newPassword = jsonObject1.optString("newPassword");

			JSONArray jsonArray2 = new JSONArray();
			JSONObject jsonObject2 = new JSONObject();

			List<User> users = userService.findByPhone(phone);
			User user = users.get(0);

			if (user.getPassword().equals(password)) {
				user.setPassword(newPassword);
				userService.updateUser(user);
				jsonObject2.put("status", "success");
			} else {
				jsonObject2.put("status", "wrongPassword");
			}

			jsonArray2.put(jsonObject2);

			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			response.getWriter().println(jsonArray2.toString());

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// 验证手机
	@RequestMapping(value = "/registerGetCode", method = RequestMethod.POST)
	public void registerGetCode(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String params = request.getParameter("registerGetCode");
			JSONArray jsonArray1 = new JSONArray(params);
			JSONObject jsonObject1 = (JSONObject) jsonArray1.get(0);

			String phone = jsonObject1.optString("phone");
			int type = jsonObject1.getInt("type");

			System.out.println("registerGetCode:" + phone + ":" + type);

			JSONArray jsonArray2 = new JSONArray();
			JSONObject jsonObject2 = new JSONObject();

			List<User> users = userService.findByPhone(phone);

			String message = null;
			String securityCode = SecurityCode.getSecurityCode();

			if (type == 1) {
				if (users.size() > 0) {
					jsonObject2.put("status", "userExist");
				} else {

					message = "【一号公交】亲爱的用户，感谢您使用一号公交app。 " + securityCode
							+ "(一号公交验证码，2分钟内有效)";
				}

			} else if (type == 2) {
				message = "【一号公交】亲爱的用户，您预约的车辆即将到站，请您及时前往公交站点乘车。";

				PS ps = new PS(phone, message);
				queue.add(ps);

				jsonObject2.put("status", "success");
				jsonObject2.put("securityCode", securityCode);

			} else if (type == 3) {

				if (users.size() == 0) {
					jsonObject2.put("status", "noUser");
				} else {

					message = "【一号公交】亲爱的用户，您的验证码是: " + securityCode
							+ "(一号公交验证码，2分钟内有效)";

					PS ps2 = new PS(phone, message);
					queue.add(ps2);

					jsonObject2.put("status", "success");
					jsonObject2.put("securityCode", securityCode);
				}

			}

			jsonArray2.put(jsonObject2);
			// 解决中文乱码问题
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			response.getWriter().println(jsonArray2.toString());

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// 获取验证码
	@RequestMapping(value = "/getSecurityCode", method = RequestMethod.POST)
	public void getSecurityCode(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("getSecurityCode");
		try {

			JSONArray jsonArray2 = new JSONArray();
			JSONObject jsonObject2 = new JSONObject();

			if (queue.size() == 0) {
				jsonObject2.put("status", "noSecurityCode");
				System.out.println("noSecurityCode");
			} else {
				PS ps = (PS) queue.poll();
				jsonObject2.put("status", "success");
				jsonObject2.put("phone", ps.getPhone());
				jsonObject2.put("securityCode", ps.getSecurityCode());
				System.out.println(ps.getPhone() + ":" + ps.getSecurityCode()
						+ "success");
			}

			jsonArray2.put(jsonObject2);
			// 解决中文乱码问题
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			response.getWriter().println(jsonArray2.toString());

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// 注册
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void register(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String params = request.getParameter("register");
			JSONArray jsonArray1 = new JSONArray(params);
			JSONObject jsonObject1 = (JSONObject) jsonArray1.get(0);

			String phone = jsonObject1.optString("phone");
			String username = jsonObject1.optString("username");
			String password = jsonObject1.optString("password");
			String cityName = jsonObject1.optString("city");

			JSONArray jsonArray2 = new JSONArray();
			JSONObject jsonObject2 = new JSONObject();

			int sex = 0;
			String avatar = "C:\\avatar\\default.jpg";
			City city = cityService.findByName(cityName).get(0);
			User user = new User(username, phone, password, sex, avatar, city);

			userService.saveUser(user);

			jsonObject2.put("status", "registerSuccess");
			jsonArray2.put(jsonObject2);
			// 解决中文乱码问题
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			response.getWriter().println(jsonArray2.toString());

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// 更改手机号
	@RequestMapping(value = "/setPhone", method = RequestMethod.POST)
	public void setPhone(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String params = request.getParameter("setPhone");
			JSONArray jsonArray1 = new JSONArray(params);
			JSONObject jsonObject1 = (JSONObject) jsonArray1.get(0);

			String phone = jsonObject1.optString("phone");
			String password = jsonObject1.optString("password");
			String newPhone = jsonObject1.optString("newPhone");

			JSONArray jsonArray2 = new JSONArray();
			JSONObject jsonObject2 = new JSONObject();

			List<User> users = userService.findByPhone(phone);
			User user = users.get(0);

			if (user.getPassword().equals(password)) {

				List<User> users2 = userService.findByPhone(newPhone);
				if (users2.size() > 0) {
					jsonObject2.put("status", "userExist");
				} else {
					String securityCode = SecurityCode.getSecurityCode();
					String message = "【一号公交】亲爱的用户，您绑定新手机的验证码是: " + securityCode
							+ "(一号公交验证码，2分钟内有效)";
					PS ps = new PS(newPhone, message);
					queue.add(ps);

					jsonObject2.put("status", "success");
					jsonObject2.put("newPhone", newPhone);
					jsonObject2.put("securityCode", securityCode);
				}

			} else {
				jsonObject2.put("status", "wrongPassword");
			}

			jsonArray2.put(jsonObject2);
			// 解决中文乱码问题
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			response.getWriter().println(jsonArray2.toString());

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// 初始化密码
	@RequestMapping("/initializePassword")
	public void initializePassword(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String params = request.getParameter("initializePassword");
			JSONArray jsonArray1 = new JSONArray(params);
			JSONObject jsonObject1 = (JSONObject) jsonArray1.get(0);

			String phone = jsonObject1.optString("phone");
			String password = jsonObject1.optString("password");

			User user = userService.findByPhone(phone).get(0);
			user.setPassword(password);
			userService.updateUser(user);

			JSONArray jsonArray2 = new JSONArray();
			JSONObject jsonObject2 = new JSONObject();

			jsonObject2.put("status", "success");

			jsonArray2.put(jsonObject2);
			// 解决中文乱码问题
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			response.getWriter().println(jsonArray2.toString());

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// 通过邮箱找回密码
	@RequestMapping("/findPasswordByEmail")
	public String findPasswordByEmail(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = request.getParameter("userName");
		List<User> users = userService.find(username);
		if (users.size() == 0) {
			System.out.println("无法找回密码，该邮箱不存在");
			return "findPassword";
		} else {
			String password = users.get(0).getPassword();
			System.out.println(username + ": " + password);
			SendEmail email = new SendEmail();
			String content = "您的密码是：" + password;
			String mailSubject = "找回密码";
			try {
				email.send(username, content, mailSubject);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("密码: " + password + " 已发送至邮箱: " + username);
			return "index";
		}
	}

}
