package com.onebus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingDeque;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;





import com.onebus.beans.User;
import com.onebus.others.Avatar;
import com.onebus.others.MPoint;
import com.onebus.others.SendEmail;
import com.onebus.service.UserService;

@Controller
@RequestMapping("/android")
public class SimulatorController {
	@Resource
	private UserService userService;
	private Map<String, List<MPoint>> mPoints;
	private Map<String, ArrayList<Integer>> queues;
	private List<String> buslines;
	private int queueIndex;
	@RequestMapping(value = "/simu", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response) {
		if (queues == null) {
			queues = new HashMap<String, ArrayList<Integer>>();
			mPoints = new HashMap<String, List<MPoint>>();
			buslines = new ArrayList<String>();
		}
		String params = request.getParameter("simulator");
		JSONObject json1;
		JSONObject json2;
		JSONObject json3;
		String busline = null;
		try {
			json1 = new JSONObject(params);
			busline = json1.optString("busLineId");
			if (!buslines.contains(busline)) {
				buslines.add(busline);
				queues.put(busline, new ArrayList<Integer>());
				ArrayList<MPoint> tem = new ArrayList<MPoint>();
				String pointString = json1.optString("points");
				String[] points = pointString.split(";");
				for (String s : points) {
					String[] p = s.split(",");
					MPoint mPoint = new MPoint(Double.parseDouble(p[0]),
							Double.parseDouble(p[1]));
					tem.add(mPoint);
				}
				mPoints.put(busline, tem);
				final String tbusline = busline;
				new Thread() {
						private int flag;
						@Override
						public void run() {
							final Timer timer = new Timer();
							timer.schedule(new TimerTask() {
								@Override
								public void run() {
								if (flag >=mPoints.get(tbusline).size()-5)
										flag = 0;
									simu();
								}
							}, 5000, 5000);
						}
						public void simu() {
							
							try {
								queues.get(tbusline).add(flag);
								flag++;
							} catch (Exception e) {
								e.printStackTrace();
							}
							System.out.println(flag + " NO");
						}
					}.start();
			}
			String result=null;
			int index=0;
			if (queues.get(busline).size() != 0) {
				 ArrayList<Integer> r=queues.get(busline);
				 index=r.size()-1;
				if(index>10)
				{
					String ss= mPoints.get(busline).get(index).toString();
					String sss=mPoints.get(busline).get(index-10).toString();
					result=ss+";"+sss;
				}else
				{
					result= mPoints.get(busline).get(index).toString();
				}
				if(queues.get(busline).size()>mPoints.get(busline).size()-10)
				{
					queues.get(busline).clear();
				}
			}
			
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			System.out.println(busline + "--"+index+":"+result);
			response.getWriter().println(result);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
