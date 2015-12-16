package com.onebus.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_goodsScore")
public class GoodsScore {

	private int id;
	private String goodsPicture;
	private String goodsName;
	private int score;

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(length = 100)
	public String getGoodsPicture() {
		return goodsPicture;
	}

	public void setGoodsPicture(String goodsPicture) {
		this.goodsPicture = goodsPicture;
	}

	@Column(length = 50)
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public GoodsScore() {
		super();
	}

	public GoodsScore(String goodsPicture, String goodsName, int score) {
		super();
		this.goodsPicture = goodsPicture;
		this.goodsName = goodsName;
		this.score = score;
	}

}
