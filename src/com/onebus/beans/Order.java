package com.onebus.beans;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_order")
public class Order {

	private int id;
	private User user;
	private Bus bus;
	private Timestamp orderTime;
	private String orderType;
	private double cash;//消费金额

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "userId", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(targetEntity = Bus.class)
	@JoinColumn(name = "busId", nullable = false)
	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@Column
	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	@Column(length = 20)
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Column
	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}



	public Order() {
	}

	public Order(User user, Bus bus, Timestamp orderTime, String orderType,
			double cash) {
		super();
		this.user = user;
		this.bus = bus;
		this.orderTime = orderTime;
		this.orderType = orderType;
		this.cash = cash;
	}

	
}
