package com.bean;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 菜品
 * 
 *
 */
@Entity
@Table(name="t_Goods")
public class Goods {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int tuijian = 0;//0:不推荐,1:推荐
	
	private int ground = 0;//0：下架，1：上架
	
	private String season;//使用季节,1春2夏3秋4冬
	
	private String goodsNum;//编号
	
	private String name;
	
	private double price;
	
	private String kouwei;
	

	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)   
	@JoinColumn(name = "goodsCateid")
	private GoodsCate goodsCate;

	public String getKouwei() {
		return kouwei;
	}

	public void setKouwei(String kouwei) {
		this.kouwei = kouwei;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTuijian() {
		return tuijian;
	}

	public void setTuijian(int tuijian) {
		this.tuijian = tuijian;
	}

	public int getGround() {
		return ground;
	}

	public void setGround(int ground) {
		this.ground = ground;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	

	public GoodsCate getGoodsCate() {
		return goodsCate;
	}

	public void setGoodsCate(GoodsCate goodsCate) {
		this.goodsCate = goodsCate;
	}
	
	
}
