package com.onebus.beans;

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
@Table(name = "t_feedback")
public class Feedback {

	private int id;
	private String userPhone;
	private Bus bus;
	private String feedbackType;// 建议||投诉
	private String content;
	private Timestamp feedbackTime;// 投诉日期
	private boolean replied; // 是否回复
	private String contactWay;// 联系方式

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
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@ManyToOne(targetEntity = Bus.class)
	@JoinColumn(name = "busId", nullable = false)
	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@Column(length = 20)
	public String getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

	@Column(length = 50)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column
	public Timestamp  getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(Timestamp feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	@Column
	public boolean isReplied() {
		return replied;
	}

	public void setReplied(boolean replied) {
		this.replied = replied;
	}

	@Column(length = 20)
	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	
	public Feedback() {
		super();
	}

	public Feedback(String userPhone, Bus bus, String feedbackType,
			String content, Timestamp feedbackTime, boolean replied,
			String contactWay) {
		super();
		this.userPhone = userPhone;
		this.bus = bus;
		this.feedbackType = feedbackType;
		this.content = content;
		this.feedbackTime = feedbackTime;
		this.replied = replied;
		this.contactWay = contactWay;
	}	

}
