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
 * 菜单
 * 
 *
 */
@Entity
@Table(name="t_BillGoods")
public class BillGoods {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false)   
	@JoinColumn(name = "billid")
	private Bill bill;
	
	private int type;//菜品标志,0:菜品，
	
	private String name;
	
	private double price;
	
	private int billNum = 1;//数量
	
	private int shang = 0;//菜是否完成，0:未完成，1:完成
	

	public int getShang() {
		return shang;
	}

	public void setShang(int shang) {
		this.shang = shang;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public int getBillNum() {
		return billNum;
	}

	public void setBillNum(int billNum) {
		this.billNum = billNum;
	}

	
}
