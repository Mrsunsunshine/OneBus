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
@Table(name = "t_travelRecord")
public class TravelRecord {

	private int id;
	private User user;
	private Bus bus;
	private Timestamp travelTime;

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
	public Timestamp getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(Timestamp travelTime) {
		this.travelTime = travelTime;
	}

	public TravelRecord(User user, Bus bus, Timestamp travelTime) {
		super();
		this.user = user;
		this.bus = bus;
		this.travelTime = travelTime;
	}
}
