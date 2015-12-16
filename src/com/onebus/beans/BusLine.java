package com.onebus.beans;

import java.sql.Time;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_busLine")
public class BusLine {

	private int id;
	private String lineNumber;
	private Company company;
	private Time SStime;//夏季开始时间
	private Time SEtime;//
	private Time WStime;//冬季开始时间
	private Time WEtime;
	private int planSchedule;//预计每日车次
	private int sentSchedule;//已发车次
	private Set<Station> stations;

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
	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	@ManyToOne(targetEntity=Company.class)
	@JoinColumn(name="companyId", nullable=false)
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column
	public Time getSStime() {
		return SStime;
	}


	public void setSStime(Time sStime) {
		SStime = sStime;
	}

	@Column
	public Time getSEtime() {
		return SEtime;
	}


	public void setSEtime(Time sEtime) {
		SEtime = sEtime;
	}

	@Column
	public Time getWStime() {
		return WStime;
	}

	public void setWStime(Time wStime) {
		WStime = wStime;
	}

	@Column
	public Time getWEtime() {
		return WEtime;
	}

	public void setWEtime(Time wEtime) {
		WEtime = wEtime;
	}

	@Column
	public int getPlanSchedule() {
		return planSchedule;
	}

	public void setPlanSchedule(int planSchedule) {
		this.planSchedule = planSchedule;
	}
	
	@Column
	public int getSentSchedule() {
		return sentSchedule;
	}

	public void setSentSchedule(int sentSchedule) {
		this.sentSchedule = sentSchedule;
	}

	@ManyToMany(targetEntity = Station.class)
	@JoinTable(name = "busLine_station", joinColumns = @JoinColumn(name = "busLineId"), inverseJoinColumns = @JoinColumn(name = "stationId"))
	public Set<Station> getStations() {
		return stations;
	}

	public void setStations(Set<Station> stations) {
		this.stations = stations;
	}

	public BusLine()
	{
		
	}
	
	public BusLine(String lineNumber, Company company, Time sStime,
			Time sEtime, Time wStime, Time wEtime, int planSchedule,
			int sentSchedule) {
		super();
		this.lineNumber = lineNumber;
		this.company = company;
		SStime = sStime;
		SEtime = sEtime;
		WStime = wStime;
		WEtime = wEtime;
		this.planSchedule = planSchedule;
		this.sentSchedule = sentSchedule;
	}
	
	

}
