package com.bean;

import java.util.ArrayList;
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
 * 菜品分类
 * 
 *
 */
@Entity
@Table(name="t_GoodsCate")
public class GoodsCate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String cateName;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH,   CascadeType.MERGE, CascadeType.REMOVE },    fetch = FetchType.LAZY, mappedBy = "goodsCate")  
	private List<Goods> goods = new ArrayList<Goods>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}
	
	
}
