package com.onebus.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_company")
public class Company {

	private int id;
	private String name;
	private String username;
	private String password;
	private City city;

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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@ManyToOne(targetEntity=City.class)
	@JoinColumn(name="cityId", nullable=false)
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Company() {
	}

	public Company(String name, String username, String password, City city) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.city = city;
	}
}
