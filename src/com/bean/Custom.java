package com.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.util.DeleteStatus;
import com.util.Sex;

/**
 * 会员
 * 
 *
 */
@Entity
@Table(name="t_Custom")
public class Custom {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Enumerated(EnumType.ORDINAL)
	private DeleteStatus deleteStatus;
	
	private String customNum;//会员编号
	
	private String name;
	
	@Enumerated(EnumType.ORDINAL)
	private Sex sex;
	
	private int age;
	
	private String mobile;
	
	private String address;
	
	private int jifen;
	
	private int clevel;//等级，500积分以下0：普通会员，500积分1：白金会员，1500积分2：黄金会员，3000积分3:钻石会员
	
	private Date createTime = new Date();
	
	private Date freshTime;//最新消费时间
	
	public String getCustomNum() {
		return customNum;
	}

	public void setCustomNum(String customNum) {
		this.customNum = customNum;
	}

	public Date getFreshTime() {
		return freshTime;
	}

	public void setFreshTime(Date freshTime) {
		this.freshTime = freshTime;
	}

	public int getClevel() {
		return clevel;
	}

	public void setClevel(int clevel) {
		this.clevel = clevel;
	}

	public DeleteStatus getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(DeleteStatus deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getJifen() {
		return jifen;
	}

	public void setJifen(int jifen) {
		this.jifen = jifen;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}
