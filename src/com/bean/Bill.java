package com.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 消费账单
 * 
 *
 */
@Entity
@Table(name="t_Bill")
public class Bill {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String searilNum;//流水号
	
	private String tableNum;//桌号
	
	private Date createTime = new Date();
	
	private double total_price;//消费总金额
	
	private double zhekou_price;//折后金额
	
	private double real_price;//实付金额
	
	private double zhaoli_price;//找零
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH,   CascadeType.MERGE, CascadeType.REMOVE },    fetch = FetchType.LAZY, mappedBy = "bill")  
	private List<BillGoods> billGoods = new ArrayList<BillGoods>();
	
	private int status = 0;//是否付钱，0:否，1:是
	
	private int guazhang;//该用户能否挂账，0：否，1：是
	
	private int finish = 0;//是否结束消费，0:否，1:是
	
	private String mobile;
	
	private int effect;//账单生效，0:否，1:是
	
	private double yudingfei;//预定费用
	
	private String zhifu;//支付方式
	
	
	public double getZhaoli_price() {
		return zhaoli_price;
	}

	public void setZhaoli_price(double zhaoli_price) {
		this.zhaoli_price = zhaoli_price;
	}

	public int getEffect() {
		return effect;
	}

	public void setEffect(int effect) {
		this.effect = effect;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getFinish() {
		return finish;
	}

	public void setFinish(int finish) {
		this.finish = finish;
	}

	public String getSearilNum() {
		return searilNum;
	}

	public void setSearilNum(String searilNum) {
		this.searilNum = searilNum;
	}

	public int getGuazhang() {
		return guazhang;
	}

	public void setGuazhang(int guazhang) {
		this.guazhang = guazhang;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public double getZhekou_price() {
		return zhekou_price;
	}

	public void setZhekou_price(double zhekou_price) {
		this.zhekou_price = zhekou_price;
	}

	public double getReal_price() {
		return real_price;
	}

	public void setReal_price(double real_price) {
		this.real_price = real_price;
	}

	public List<BillGoods> getBillGoods() {
		return billGoods;
	}

	public void setBillGoods(List<BillGoods> billGoods) {
		this.billGoods = billGoods;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
