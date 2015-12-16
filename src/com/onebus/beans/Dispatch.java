package com.onebus.beans;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_dispatch")
public class Dispatch {

	private int id;
	private Company company;
	private Bus bus;
	private Driver driver;
	private Date dispatchDate;// 调度日期
	private Time dispatchTime;// 发车时间
	private String busType;// 车辆类型

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = Company.class)
	@JoinColumn(name = "companyId", nullable = false)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(targetEntity = Bus.class)
	@JoinColumn(name = "busId", nullable = false)
	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@ManyToOne(targetEntity = Driver.class)
	@JoinColumn(name = "driverId", nullable = false)
	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Column
	public Date getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	@Column
	public Time getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(Time dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	@Column(length = 20)
	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

}
