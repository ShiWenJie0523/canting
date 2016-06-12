package com.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 餐桌
 * 
 *
 */
@Entity
@Table(name="t_DiningTable")
public class DiningTable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String tableNum;
	
	private int seatCount;
	
	private int status;//0:空，1：有人，2：已预定，3：预定超时
	
	private String mobile;
	
	private String name;
	
	private Date bookTime;
	
	private double yudingfei;//预定支付
	
	private String zhifu;//支付方式

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTableNum() {
		return tableNum;
	}

	public void setTableNum(String tableNum) {
		this.tableNum = tableNum;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBookTime() {
		return bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}

	public double getYudingfei() {
		return yudingfei;
	}

	public void setYudingfei(double yudingfei) {
		this.yudingfei = yudingfei;
	}

	public String getZhifu() {
		return zhifu;
	}

	public void setZhifu(String zhifu) {
		this.zhifu = zhifu;
	}
	
	
}
