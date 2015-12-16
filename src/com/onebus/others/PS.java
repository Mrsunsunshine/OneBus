package com.onebus.others;

public class PS {
	private String phone;
	private String securityCode;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public PS(String phone, String securityCode) {
		super();
		this.phone = phone;
		this.securityCode = securityCode;
	}
	

}
