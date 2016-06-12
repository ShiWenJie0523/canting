package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.bean.Goods;
import com.bean.GoodsCate;
import com.dao.CommonDao;
import com.util.ActionUtil;
import com.util.ClassBeanFactory;
import com.util.Utils;

/**
 * 商品，采购，销售
 * 
 *
 */
public class GoodsAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommonDao commDao = (CommonDao)ClassBeanFactory.getBean("commDao");
	
	public String goodscateList(){
		long count = commDao.getObjectCount(GoodsCate.class);
		List<GoodsCate> list = commDao.getObjectList(GoodsCate.class, getPageNum(), getNumPerPage());
		setAttribute("list", list);
		setAttribute("totalCount", count);
		return ActionUtil.Result.SUCCESS;
	}
	
	public String addGoodsCate() throws IOException{
		GoodsCate bean = new GoodsCate();
		bean.setCateName(getParameter("cateName"));
		commDao.addObject(bean);
		PrintWriter out = getWriter();
		out.write(Utils.outPutMsg("200", "添加成功", "goodscateList", "", false, "act_Goods_goodscateList.action"));
		out.flush();
		return null;
	}
	
	public String deleGoodsCate() throws IOException{
		PrintWriter out = getWriter();
		int id = Integer.parseInt(getParameter("id"));
		commDao.deleObject(GoodsCate.class, id);
		out.write(Utils.outPutMsg("200", "删除成功", "", "", false, ""));
		out.flush();
		return null;
	}
	
	public String updateGoodsCate() throws IOException{
		int id = Integer.parseInt(getParameter("id"));
		GoodsCate bean = commDao.findObjectById(GoodsCate.class, id);
		bean.setCateName(getParameter("cateName"));
		commDao.updateObject(bean);
		PrintWriter out = getWriter();
		out.write(Utils.outPutMsg("200", "修改成功", "goodscateList", "", false, "act_Goods_goodscateList.action"));
		out.flush();
		return null;
	}
	
	public String findGoodsCate(){
		int id = Integer.parseInt(getParameter("id"));
		GoodsCate bean = commDao.findObjectById(GoodsCate.class, id);
		setAttribute("bean", bean);
		return ActionUtil.Result.SUCCESS;
	}
	
	
	
	
	
	
	
	public String goodsList(){
		int cateId = Integer.parseInt(getParameter("cateId"));
		StringBuffer where = new StringBuffer();
		where.append(" where goodsCateid=?");
		Object[] objArr = createNames(cateId);
		long count = commDao.getObjectCount(Goods.class,where.toString(),objArr);
		List<Goods> list = commDao.getObjectList(Goods.class,where.toString(),objArr, getPageNum(), getNumPerPage());
		setAttribute("list", list);
		setAttribute("cateId", cateId);
		setAttribute("totalCount", count);
		return ActionUtil.Result.SUCCESS;
	}
	
	public String addGoodsForm(){
	
		setAttribute("cateId", getParameter("cateId"));
		return ActionUtil.Result.SUCCESS;
	}
	
	public String addGoods() throws IOException{
		int cateId = Integer.parseInt(getParameter("cateId"));
		GoodsCate goodsCate = commDao.findObjectById(GoodsCate.class, cateId);
		Goods bean = new Goods();
		bean.setGoodsCate(goodsCate);
		bean.setPrice(Double.parseDouble(getParameter("price")));
		bean.setName(getParameter("name"));
		bean.setGoodsNum(getParameter("goodsNum"));
		bean.setKouwei(getParameter("kouwei"));
		String[] str = getReq().getParameterValues("season");
		StringBuffer season = new StringBuffer();
		if(str!=null){
			for(int i=0;i<str.length;i++){
				season.append(str[i]+",");
			}
		}
		bean.setSeason(season.toString());
		bean.setTuijian(Integer.parseInt(getParameter("tuijian")));
		bean.setGround(Integer.parseInt(getParameter("ground")));
		PrintWriter out = getWriter();
		if(commDao.getObjectCount(Goods.class, " where goodsNum=?", bean.getGoodsNum())>0){
			out.write(Utils.outPutMsg("300", "添加失败，该菜品编号已存在", "", "", false, ""));
			out.flush();
			return null;
		}
		commDao.addObject(bean);
		out.write(Utils.outPutMsg("200", "添加成功", "goodsList", "", true, "act_Goods_goodsList.action?cateId="+cateId));
		out.flush();
		return null;
	}
	
	public String deleGoods() throws IOException{
		PrintWriter out = getWriter();
		int id = Integer.parseInt(getParameter("id"));
		commDao.deleObject(Goods.class, id);
		out.write(Utils.outPutMsg("200", "删除成功", "", "", false, ""));
		out.flush();
		return null;
	}
	
	public String updateGoods() throws IOException{
		int id = Integer.parseInt(getParameter("id"));
		Goods bean = commDao.findObjectById(Goods.class, id);
		bean.setPrice(Double.parseDouble(getParameter("price")));
		bean.setName(getParameter("name"));
		String[] str = getReq().getParameterValues("season");
		StringBuffer season = new StringBuffer();
		if(str!=null){
			for(int i=0;i<str.length;i++){
				season.append(str[i]+",");
			}
		}
		bean.setSeason(season.toString());
		bean.setTuijian(Integer.parseInt(getParameter("tuijian")));
		bean.setGround(Integer.parseInt(getParameter("ground")));
		bean.setKouwei(getParameter("kouwei"));
		PrintWriter out = getWriter();
		commDao.updateObject(bean);
		out.write(Utils.outPutMsg("200", "修改成功", "goodsList", "", true, "act_Goods_goodsList.action?cateId="+bean.getGoodsCate().getId()));
		out.flush();
		return null;
	}
	
	public String findGoods(){

		int id = Integer.parseInt(getParameter("id"));
		Goods bean = commDao.findObjectById(Goods.class, id);
		StringBuffer season = new StringBuffer();
		String[] str = bean.getSeason().split(",");
		String[] s = {"春","夏","秋","冬"};
		for(int j=0;j<s.length;j++){
			String tmp = "";
			for(int i=0;i<str.length;i++){
				if(s[j].equals(str[i])){
					tmp = "<input type=\"checkbox\" name=\"season\" value=\""+s[j]+"\" checked=\"checked\"/>"+s[j];
				}
			}
			if(tmp.equals("")){
				season.append("<input type=\"checkbox\" name=\"season\" value=\""+s[j]+"\"/>"+s[j]);
			}else{
				season.append(tmp);
			}
		}
		setAttribute("season", season.toString());
		setAttribute("bean", bean);
		return ActionUtil.Result.SUCCESS;
	}
	
	
	
	
	

	
	
	
	
}
