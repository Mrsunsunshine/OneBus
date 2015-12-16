package com.onebus.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_manager")
public class Manager {

	private int id;
	private String username;
	private String email;
	private String password;
	private String phone;
	private String address;
	private String number;
	private Company company;

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(length = 20)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(length = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(length = 20)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(length = 20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(length = 20)
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(length = 20)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@ManyToOne(targetEntity=Company.class)
	@JoinColumn(name="companyId", nullable=false)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Manager()
	{
		
	}

	public Manager(String username, String email, String password,
			String phone, String address, String number, Company company) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.number = number;
		this.company = company;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", username=" + username + ", email="
				+ email + ", password=" + password + ", phone=" + phone
				+ ", address=" + address + ", number=" + number + ", company="
				+ company + "]";
	}
	
	
}
