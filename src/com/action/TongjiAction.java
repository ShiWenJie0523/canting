package com.action;

import java.util.ArrayList;
import java.util.List;

import com.dao.CommonDao;
import com.util.ActionUtil;
import com.util.ClassBeanFactory;

public class TongjiAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private CommonDao commDao = (CommonDao)ClassBeanFactory.getBean("commDao");
	

	
	
	/**
	 * 日明细统计
	 * mysql统计
	 * @return
	 */
	public String dayMingxi(){

		List<Object[]> mingxi = new ArrayList<Object[]>();
	
		List<Object> list = commDao.getSqlList("select DATE_FORMAT(createTime,'%Y-%m-%d') from t_bill group by DATE_FORMAT(createTime,'%Y-%m-%d')");
		
		
		
		for(int i=0;i<list.size();i++){
			Object[] str = new Object[5];
			Object obj = (Object)list.get(i);
			str[0] = (String)obj;
			//挂账
			List<Object> guazhngList = commDao.getSqlList("select sum(guazhang) from t_bill where guazhang=1 and finish=1 and DATE_FORMAT(createTime,'%Y-%m-%d')='"+obj+"'");
			Object guazhng = (Object)guazhngList.get(0);
			if(guazhng!=null){
				str[1] = guazhng;
			}else{
				str[1] = 0;
			}
			//收入
			List<Object> shouruList = commDao.getSqlList("select sum(zhekou_price) from t_bill where finish=1 and DATE_FORMAT(createTime,'%Y-%m-%d')='"+obj+"'");
			
			
			
			Object shouru = (Object)shouruList.get(0);
			if(shouru!=null){
				str[2] = shouru;
			}else{
				str[2] = 0;
			}
			//菜品
			List<Object> goodsList = commDao.getSqlList("select sum(billNum) from t_bill b,t_billgoods g where b.id=g.billid and g.type=0 and DATE_FORMAT(createTime,'%Y-%m-%d')='"+obj+"'");
			Object goods = (Object)goodsList.get(0);
			if(goods!=null){
				str[3] = goods;
			}else{
				str[3] = 0;
			}
			
			mingxi.add(str);
		}
		setAttribute("mingxi", mingxi);
		return ActionUtil.Result.SUCCESS;
	}
	
	/**
	 * 明细统计
	 * oracle统计
	 * @return
	 *//*
	public String dayMingxi(){

		List<Object[]> mingxi = new ArrayList<Object[]>();
	
		List<Object> list = commDao.getSqlList("select to_char(createTime,'yyyy-mm-dd') from t_bill group by  to_char(createTime,'yyyy-mm-dd')");
		
		
		
		for(int i=0;i<list.size();i++){
			Object[] str = new Object[5];
			Object obj = (Object)list.get(i);
			str[0] = (String)obj;
			//挂账
			List<Object> guazhngList = commDao.getSqlList("select sum(guazhang) from t_bill where guazhang=1 and finish=1 and to_char(createTime,'yyyy-mm-dd')='"+obj+"'");
			Object guazhng = (Object)guazhngList.get(0);
			if(guazhng!=null){
				str[1] = guazhng;
			}else{
				str[1] = 0;
			}
			//收入
			List<Object> shouruList = commDao.getSqlList("select sum(zhekou_price) from t_bill where finish=1 and to_char(createTime,'yyyy-mm-dd')='"+obj+"'");
			
			
			
			Object shouru = (Object)shouruList.get(0);
			if(shouru!=null){
				str[2] = shouru;
			}else{
				str[2] = 0;
			}
			//菜品
			List<Object> goodsList = commDao.getSqlList("select sum(billNum) from t_bill b,t_billgoods g where b.id=g.billid and g.type=0 and to_char(createTime,'yyyy-mm-dd')='"+obj+"'");
			Object goods = (Object)goodsList.get(0);
			if(goods!=null){
				str[3] = goods;
			}else{
				str[3] = 0;
			}
			//套餐
			List<Object> taocanList = commDao.getSqlList("select sum(billNum) from t_bill b,t_billgoods g where b.id=g.billid and g.type=1 and to_char(createTime,'yyyy-mm-dd')='"+obj+"'");
			Object taocan = (Object)taocanList.get(0);
			if(taocan!=null){
				str[4] = taocan;
			}else{
				str[4] = 0;
			}
			mingxi.add(str);
		}
		setAttribute("mingxi", mingxi);
		return ActionUtil.Result.SUCCESS;
	}*/
	
	
	/**
	 * sqlserver统计
	 * @return
	 */
	
	/*public String dayMingxi(){

		List<Object[]> mingxi = new ArrayList<Object[]>();
		
		
		//sqlserver的统计
		List<Object> list = commDao.getSqlList("select  REPLACE(CONVERT(varchar, createTime, 111), '/', '-')  from  [shujuku_db].[dbo].[t_Bill]  group by REPLACE(CONVERT(varchar, createTime, 111), '/', '-') ");
		
		for(int i=0;i<list.size();i++){
			Object[] str = new Object[5];
			Object obj = (Object)list.get(i);
			str[0] = (String)obj;
			//挂账
			List<Object> guazhngList = commDao.getSqlList("select sum(guazhang) from [shujuku_db].[dbo].[t_Bill] where guazhang=1 and finish=1 and REPLACE(CONVERT(varchar, createTime, 111), '/', '-')='"+obj+"'");
			Object guazhng = (Object)guazhngList.get(0);
			if(guazhng!=null){
				str[1] = guazhng;
			}else{
				str[1] = 0;
			}
			//收入
			
			List<Object> shouruList = commDao.getSqlList("select sum(zhekou_price) from [shujuku_db].[dbo].[t_Bill] where finish=1 and REPLACE(CONVERT(varchar, createTime, 111), '/', '-')='"+obj+"'");
			
			
			Object shouru = (Object)shouruList.get(0);
			if(shouru!=null){
				str[2] = shouru;
			}else{
				str[2] = 0;
			}
			//菜品
			
			List<Object> goodsList = commDao.getSqlList("select sum(billNum) from [shujuku_db].[dbo].[t_Bill] b,t_BillGoods g where b.id=g.billid and g.type=0 and REPLACE(CONVERT(varchar, createTime, 111), '/', '-')='"+obj+"'");
			
			
			Object goods = (Object)goodsList.get(0);
			if(goods!=null){
				str[3] = goods;
			}else{
				str[3] = 0;
			}
			//套餐
			List<Object> taocanList = commDao.getSqlList("select sum(billNum) from [shujuku_db].[dbo].[t_Bill] b,t_BillGoods g where b.id=g.billid and g.type=1 and REPLACE(CONVERT(varchar, createTime, 111), '/', '-')='"+obj+"'");
			
			
			Object taocan = (Object)taocanList.get(0);
			if(taocan!=null){
				str[4] = taocan;
			}else{
				str[4] = 0;
			}
			mingxi.add(str);
		}
		setAttribute("mingxi", mingxi);
		return ActionUtil.Result.SUCCESS;
	}*/
	
	
	
	/**
	 * 日结算
	 * @return
	 */
	public String dayJiesuan(){
		return ActionUtil.Result.SUCCESS;
	}
	
	
	
	
	
}
