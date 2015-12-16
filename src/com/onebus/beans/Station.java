package com.onebus.beans;

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
@Table(name = "t_station")
public class Station {

	private int id;
	private City city;
	private String district;// 区（莲湖区）
	private String name;
	private double latitude;// 纬
	private double longitude;// 经度
	private Set<BusLine> busLines;

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = City.class)
	@JoinColumn(name = "cityId", nullable = false)
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Column(length = 20)
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Column
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@ManyToMany(targetEntity = BusLine.class)
	@JoinTable(name = "busLine_station", joinColumns = @JoinColumn(name = "stationId"), inverseJoinColumns = @JoinColumn(name = "busLineId"))
	public Set<BusLine> getBusLines() {
		return busLines;
	}

	public void setBusLines(Set<BusLine> busLines) {
		this.busLines = busLines;
	}

	public Station() {
		super();
	}

	public Station(City city, String district, String name, double latitude,
			double longitude) {
		super();
		this.city = city;
		this.district = district;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Station(City city, String district, String name, double latitude,
			double longitude, Set<BusLine> busLines) {
		super();
		this.city = city;
		this.district = district;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.busLines = busLines;
	}
	

}
