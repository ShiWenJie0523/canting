package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import com.bean.Custom;
import com.util.DeleteStatus;
import com.util.Sex;
import com.dao.CommonDao;
import com.util.ActionUtil;
import com.util.ClassBeanFactory;
import com.util.Utils;

/**
 * 会员
 * 
 *
 */
public class CustomAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommonDao commDao = (CommonDao)ClassBeanFactory.getBean("commDao");
	
	public String customList(){
		StringBuffer where = new StringBuffer();
		where.append(" where deleteStatus=?");
		Object[] objArr = createNames(DeleteStatus.NO);
		String customNum = getParameter("customNum");
		if(!customNum.equals("")){
			where.append(" and customNum=?");
			objArr = addNames(objArr, customNum);
		}
		String name = getParameter("name");
		if(!name.equals("")){
			where.append(" and name=?");
			objArr = addNames(objArr, name);
		}
		long count = commDao.getObjectCount(Custom.class,where.toString(),objArr);
		List<Custom> list = commDao.getObjectList(Custom.class,where.toString(),objArr, getPageNum(), getNumPerPage());
		setAttribute("list", list);
		setAttribute("totalCount", count);
		return ActionUtil.Result.SUCCESS;
	}
	
	public String addCustom() throws IOException{
		
		StringBuffer where = new StringBuffer();
		where.append(" where deleteStatus=?");
		Object[] objArr = createNames(DeleteStatus.NO);
		
		String mobile = getParameter("mobile");
		if(!mobile.equals("")){
			where.append(" and mobile=?");
			objArr = addNames(objArr, mobile);
		}
		
		long count = commDao.getObjectCount(Custom.class,where.toString(),objArr);
		
		if(count>0){
			PrintWriter out = getWriter();
			out.write(Utils.outPutMsg("200", "添加失败，该会员号码已经存在！！", "customList", "", true, "act_Custom_customList.action"));
			out.flush();
			return null;
		}
		
		Custom bean = new Custom();
		bean.setDeleteStatus(DeleteStatus.NO);
		bean.setCustomNum(Utils.date2Str(new Date(), "MMddHHmmss"));
		bean.setName(getParameter("name"));
		bean.setSex(Sex.valueOf(getParameter("sex")));
		bean.setAge(Integer.parseInt(getParameter("age")));
		bean.setMobile(getParameter("mobile"));
		bean.setAddress(getParameter("address"));
		bean.setJifen(0);
		bean.setClevel(0);
		commDao.addObject(bean);
		PrintWriter out = getWriter();
		out.write(Utils.outPutMsg("200", "添加成功", "customList", "", true, "act_Custom_customList.action"));
		out.flush();
		return null;
	}
	
	public String deleCustom() throws IOException{
		PrintWriter out = getWriter();
		int id = Integer.parseInt(getParameter("id"));
		Custom bean = commDao.findObjectById(Custom.class, id);
		bean.setDeleteStatus(DeleteStatus.YES);
		commDao.updateObject(bean);
		out.write(Utils.outPutMsg("200", "删除成功", "", "", false, ""));
		out.flush();
		return null;
	}
	
	public String updateCustom() throws IOException{
		int id = Integer.parseInt(getParameter("id"));
		Custom bean = commDao.findObjectById(Custom.class, id);
		bean.setName(getParameter("name"));
		bean.setSex(Sex.valueOf(getParameter("sex")));
		bean.setMobile(getParameter("mobile"));
		bean.setAddress(getParameter("address"));
		bean.setAge(Integer.parseInt(getParameter("age")));
		commDao.updateObject(bean);
		PrintWriter out = getWriter();
		out.write(Utils.outPutMsg("200", "修改成功", "customList", "", true, "act_Custom_customList.action"));
		out.flush();
		return null;
	}
	
	public String findCustom(){
		int id = Integer.parseInt(getParameter("id"));
		Custom bean = commDao.findObjectById(Custom.class, id);
		setAttribute("bean", bean);
		return ActionUtil.Result.SUCCESS;
	}
}
