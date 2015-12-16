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
@Table(name = "t_bus")
public class Bus {

	private int id;
	private BusLine busLine;
	private Company company;
	private String plateNumber;// 车牌号
	private boolean started = false;// 是否出发
	private String busType;// 车辆类型
	private double price;

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = BusLine.class)
	@JoinColumn(name = "busLineId", nullable = false)
	public BusLine getBusLine() {
		return busLine;
	}

	public void setBusLine(BusLine busLine) {
		this.busLine = busLine;
	}

	@ManyToOne(targetEntity = Company.class)
	@JoinColumn(name = "companyId", nullable = false)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(length = 20)
	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	@Column
	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}
	

	@Column(length = 20)
	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}
	
	@Column
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Bus() {
		super();
	}

	public Bus(BusLine busLine, Company company, String plateNumber,
			boolean started, String busType) {
		super();
		this.busLine = busLine;
		this.company = company;
		this.plateNumber = plateNumber;
		this.started = started;
		this.busType = busType;
	}

	public Bus(BusLine busLine, Company company, String plateNumber,
			String busType, double price) {
		super();
		this.busLine = busLine;
		this.company = company;
		this.plateNumber = plateNumber;
		this.busType = busType;
		this.price = price;
	}
	

	

}
