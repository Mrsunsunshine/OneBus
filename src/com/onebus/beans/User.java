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
@Table(name = "t_user")
public class User {

	private int id;
	private String username;
	private String phone;
	private String password;
	private String cardNumber;
	private double balance;
	private int score;
	private int sex;
	private String avatar;	//头像 存储路径
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
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(length = 20)
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Column
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	@Column
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Column
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Column(length = 100)
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@ManyToOne(targetEntity=City.class)
	@JoinColumn(name="cityId", nullable=false)
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public User()
	{
		
	}

	public User(String username, String phone, String password,
			String cardNumber, double balance, int sex, String avatar) {
		super();
		this.username = username;
		this.phone = phone;
		this.password = password;
		this.cardNumber = cardNumber;
		this.balance = balance;
		this.sex = sex;
		this.avatar = avatar;
	}

	public User(String username, String phone, String password, int sex,
			String avatar, City city) {
		super();
		this.username = username;
		this.phone = phone;
		this.password = password;
		this.sex = sex;
		this.avatar = avatar;
		this.city = city;
	}

	
	
}
