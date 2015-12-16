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
@Table(name = "t_driver")
public class Driver {

	private int id;
	private String number;
	private String name;
	private int age;
	private String sex;
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
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 20)
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Column(length = 20)
	public String getSex() {
		return sex;
	}

	
	public void setSex(String sex) {
		this.sex = sex;
	}

	@ManyToOne(targetEntity=Company.class)
	@JoinColumn(name="companyId", nullable=false)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Driver()
	{
		
	}
	
	public Driver(String number, String name, int age, String sex, Company company) {
		super();
		this.number = number;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.company = company;
	}

	

	
}
