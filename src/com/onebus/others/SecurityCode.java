package com.onebus.others;

import java.util.Random;


public class SecurityCode {

	public static String getSecurityCode() {
		Random rd = new Random();
		String n = "";
		int getNum;
		do {
			getNum = Math.abs(rd.nextInt()) % 10 + 48;
			char num1 = (char) getNum;
			String dn = Character.toString(num1);
			n += dn;
		} while (n.length() < 6);
		System.out.println("随机的6位密码是：" + n);
		return n;
	}
}
