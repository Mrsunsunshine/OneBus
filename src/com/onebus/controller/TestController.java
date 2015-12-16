package com.onebus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController //通过此就访问不了jsp
@Controller
@RequestMapping("/main")
public class TestController {
	


	@RequestMapping(value = "/test")
	public String test(HttpServletRequest request, HttpServletResponse response) {
		
		return "test";
	}


}
