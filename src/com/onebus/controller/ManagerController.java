package com.onebus.controller;

import java.security.NoSuchAlgorithmException;
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

import com.onebus.beans.Company;
import com.onebus.beans.Driver;
import com.onebus.beans.Manager;
import com.onebus.others.MD5;
import com.onebus.others.SendEmail;
import com.onebus.service.CompanyService;
import com.onebus.service.ManagerService;

@Controller
@RequestMapping("/main")
public class ManagerController {
	@Resource
	private ManagerService managerService;
	@Resource
	private CompanyService companyService;

	// 找回密码界面
	@RequestMapping("/findPassword")
	public String findPassword(Model model) {
		return "findPassword";
	}

	// 返回登陆界面
	@RequestMapping("/index")
	public String index(Model model) {
		return "index";
	}

	// 通过邮箱找回密码
	@RequestMapping("/findPasswordByEmail")
	public String findPasswordByEmail(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		List<Manager> managers = managerService.find(username);
		if (managers.size() == 0) {
			request.setAttribute("find", "noUser");
			System.out.println("无法找回密码，该用户不存在");
			return "findPassword";
		} else {
			String managerEamil = managers.get(0).getEmail();
			if (!managerEamil.equals(email)) {
				request.setAttribute("find", "noEmail");
				System.out.println("无法找回密码，邮箱不匹配");
				return "findPassword";
			} else {
				Manager manager = managers.get(0);
				String password = null;
				try {
					password = MD5.getMD5("123456");
				} catch (NoSuchAlgorithmException e1) {
					e1.printStackTrace();
				}
				manager.setPassword(password);
				managerService.updateManager(manager);
				System.out.println(username + ": " + password + ": "
						+ managerEamil);

				String content = "您的密码被重置为：" + 123456 + " 请登陆后及时修改！";
				String mailSubject = "找回密码";
				try {
					managerService.send(email, content, mailSubject);

				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(username + " 的密码: " + password + " 已发送至邮箱: "
						+ managerEamil);
				return "index";

			}

		}
	}

	// 检查登陆信息是否有误
	@RequestMapping("/checkManager")
	public String checkManager(HttpServletRequest request,
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);

		String md5Password = null;
		try {
			md5Password = MD5.getMD5(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// 进入超级管理员界面
		List<Company> companys = companyService.find(username);
		if (companys.size() > 0) {
			Company company = companyService.find(username).get(0);
			String password2 = company.getPassword();

			if (password2.equals(md5Password)) {
				request.getSession().setAttribute("companyId", company.getId());
				List<Manager> managers = managerService.getAll(company.getId());
				request.setAttribute("managers", managers);
				return "managerInfor";
			}
		}

		List<Manager> managers = managerService.find(username);
		if (managers.size() == 0) {
			System.out.println("用户不存在");
			request.setAttribute("login", "noUser");
			return "index";
		} else {

			if (md5Password.equals(managers.get(0).getPassword())) {
				request.setAttribute("login", "success");

				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("company",
						managers.get(0).getCompany());

				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(currentTime);
				request.setAttribute("now", dateString);
				return "main";
			} else {
				request.setAttribute("login", "wrongPassword");
				System.out.println("密码错误");
				return "index";

			}
		}
	}

	// 转到主界面
	@RequestMapping("/home")
	public String home(HttpServletRequest request, HttpServletResponse response) {

		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		request.setAttribute("now", dateString);
		return "main";

	}

	// 转到修改密码界面
	@RequestMapping("/setting")
	public String setting(HttpServletRequest request,
			HttpServletResponse response) {

		String username = (String) request.getSession()
				.getAttribute("username");

		return "setting";

	}

	// 修改密码
	@RequestMapping("/setPassword")
	public String setPassword(HttpServletRequest request,
			HttpServletResponse response) {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("password2");

		String md5Password = null;
		String newMd5Password = null;
		try {
			md5Password = MD5.getMD5(password);
			newMd5Password = MD5.getMD5(newPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		System.out.println(username + ":" + password + ":" + newPassword);

		List<Manager> managers = managerService.find(username);
		if (managers.size() == 0) {
			System.out.println("用户正确");
			request.setAttribute("sp", "noUser");
			return "setting";
		} else {

			Manager manager = managers.get(0);
			if (md5Password.equals(manager.getPassword())) {

				manager.setPassword(newMd5Password);
				managerService.updateManager(manager);
				System.out.println("更改密码成功");
				request.setAttribute("sp", "success");

				return "setting";
			} else {
				request.setAttribute("sp", "wrongPassword");
				System.out.println("密码错误");
				return "setting";

			}
		}

	}

	// 转到修改信息界面
	@RequestMapping("/settingInformation")
	public String settingInformation(HttpServletRequest request,
			HttpServletResponse response) {

		String username = (String) request.getSession()
				.getAttribute("username");
		List<Manager> managers = managerService.find(username);
		Manager manager = managers.get(0);
		request.setAttribute("manager", manager);
		return "settingInformation";

	}

	// 修改信息
	@RequestMapping("/submitInfor")
	public String submitInfor(HttpServletRequest request,
			HttpServletResponse response) {
		String username = (String) request.getSession()
				.getAttribute("username");
		String newUsername = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		System.out.println(username + ":" + newUsername);

		// 判断username是否重复
		if (!username.equals(newUsername)) {
			List<Manager> managers0 = managerService.find(newUsername);
			if (managers0.size() > 0) {

				request.setAttribute("si", "usernameExist");
				System.out.println("该用户名已存在！");
				return "settingInformation";

			}
		}
		// 判断email是否重复
		List<Manager> managers1 = managerService.findByEmail(email);
		if (managers1.size() > 0) {
			if (!managers1.get(0).getUsername().equals(username)) {
				request.setAttribute("si", "emailExist");
				System.out.println("该邮箱已存在！");
				return "settingInformation";
			}
		}

		// 判断电话是否重复
		if (!phone.equals("")) {
			List<Manager> managers2 = managerService.findByPhone(phone);
			if (managers2.size() > 0) {
				if (!managers2.get(0).getUsername().equals(username)) {
					request.setAttribute("si", "phoneExist");
					System.out.println("该电话已存在！");
					return "settingInformation";
				}
			}
		}

		// 满足条件 修改信息
		List<Manager> managers = managerService.find(username);
		Manager manager = managers.get(0);
		manager.setUsername(newUsername);
		manager.setEmail(email);
		manager.setPhone(phone);
		manager.setAddress(address);

		managerService.updateManager(manager);
		System.out.println("更改用户信息成功");
		request.setAttribute("si", "success");
		return "settingInformation";

	}

	// 转到超级管理员主页
	@RequestMapping("/managerInfor")
	public String managerInfor(HttpServletRequest request,
			HttpServletResponse response) {
		int companyId = (int) request.getSession().getAttribute("companyId");
		List<Manager> managers = managerService.getAll(companyId);
		request.setAttribute("managers", managers);

		return "managerInfor";

	}

	// 超级管理员删除管理员
	@RequestMapping("/deleteManager")
	public String deleteManager(HttpServletRequest request,
			HttpServletResponse response) {

		String number = request.getParameter("number");
		Manager manager = managerService.findByNumber(number).get(0);
		managerService.delete(manager);

		int companyId = (int) request.getSession().getAttribute("companyId");
		List<Manager> managers = managerService.getAll(companyId);
		request.setAttribute("managers", managers);

		return "managerInfor";

	}

	// 超级管理员转到编辑管理员界面
	@RequestMapping("/editManager")
	public String editManager(HttpServletRequest request,
			HttpServletResponse response) {

		String number = request.getParameter("number");
		Manager manager = managerService.findByNumber(number).get(0);

		request.setAttribute("manager", manager);
		return "editManager";

	}

	// 超级管理员编辑管理员
	@RequestMapping("/submitManagerInfor")
	public String submitManagerInfor(HttpServletRequest request,
			HttpServletResponse response) {
		String number = request.getParameter("number");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		Manager manager = managerService.findByNumber(number).get(0);
		manager.setUsername(username);
		manager.setEmail(email);
		manager.setPhone(phone);
		manager.setAddress(address);

		System.out.println(manager);
		managerService.updateManager(manager);

		request.setAttribute("sEM", "editSuccess");

		int companyId = (int) request.getSession().getAttribute("companyId");
		List<Manager> managers = managerService.getAll(companyId);
		request.setAttribute("managers", managers);

		return "managerInfor";
	}

	// 超级管理员转到添加管理员界面
	@RequestMapping("/addManager")
	public String addManager(HttpServletRequest request,
			HttpServletResponse response) {

		return "addManager";

	}

	// 超级管理员添加管理员
	@RequestMapping("/submitAddManager")
	public String submitAddManager(HttpServletRequest request,
			HttpServletResponse response) {

		String number = request.getParameter("number");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");

		String md5Password = null;
		try {
			md5Password = MD5.getMD5(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// 判断username是否重复
		List<Manager> managers4 = managerService.findByNumber(number);
		if (managers4.size() > 0) {

			request.setAttribute("sAM", "numberExist");
			System.out.println("该工号已存在！");
			return "addManager";

		}

		// 判断username是否重复
		List<Manager> managers0 = managerService.find(username);
		if (managers0.size() > 0) {

			request.setAttribute("sAM", "usernameExist");
			System.out.println("该用户名已存在！");
			return "addManager";

		}

		// 判断email是否重复
		List<Manager> managers1 = managerService.findByEmail(email);
		if (managers1.size() > 0) {
			if (!managers1.get(0).getUsername().equals(username)) {
				request.setAttribute("sAM", "emailExist");
				System.out.println("该邮箱已存在！");
				return "addManager";
			}
		}

		// 判断电话是否重复
		if (!phone.equals("")) {
			List<Manager> managers2 = managerService.findByPhone(phone);
			if (managers2.size() > 0) {
				if (!managers2.get(0).getUsername().equals(username)) {
					request.setAttribute("sAM", "phoneExist");
					System.out.println("该电话已存在！");
					return "addManager";
				}
			}
		}

		int companyId = (int) request.getSession().getAttribute("companyId");

		Company company = companyService.findById(companyId).get(0);

		Manager manager = new Manager(username, email, md5Password, phone,
				address, number, company);
		managerService.saveManager(manager);

		request.setAttribute("sAM", "addSuccess");

		List<Manager> managers = managerService.getAll(companyId);
		request.setAttribute("managers", managers);

		return "managerInfor";

	}

}
