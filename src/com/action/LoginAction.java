package com.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.Role;
import com.bean.UserBean;
import com.dao.UserDao;
import com.util.ActionUtil;
import com.util.ClassBeanFactory;
import com.util.MenuUtil;


public class LoginAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserDao userDao = (UserDao)ClassBeanFactory.getBean("userDao");

	
	@SuppressWarnings("unused")
	private void session(){
		String username = (String)getSession(ActionUtil.SESSION_USERNAME);
		setSession(ActionUtil.SESSION_ID, userDao.findUserByName(username).getId());
		String role = (String)getSession(ActionUtil.SESSION_ROLE);
		setSession("roleNum", Role.valueOf(role).ordinal());
	}
	
	public String index(){
		//左边导航
		String role = (String)getSession(ActionUtil.SESSION_ROLE);
		StringBuffer sb = new StringBuffer();
		
		if(Role.ROLE_ROOT.toString().equals(role)){
			MenuUtil sysApp = new MenuUtil("系统管理");
			sysApp.createMenu("act_User_userList.action","navTab","userList","收银员管理");
			sysApp.createMenu("act_Custom_customList.action","navTab","customList","会员管理");
			sb.append(sysApp.toString());

			MenuUtil goods = new MenuUtil("菜品管理");
			goods.createMenu("act_Goods_goodscateList.action","navTab","goodscateList","菜品管理");
			sb.append(goods.toString());
			
			MenuUtil seat = new MenuUtil("桌位管理");
			seat.createMenu("act_Bill_diningtableList.action","navTab","diningtableList","桌位管理");
			sb.append(seat.toString());
			
			MenuUtil tongji = new MenuUtil("统计管理");
			tongji.createMenu("act_Tongji_dayMingxi.action","navTab","dayMingxi","日明细统计");
	
			sb.append(tongji.toString());
		}
		if(Role.ROLE_CASH.toString().equals(role)){
			MenuUtil sysApp = new MenuUtil("系统管理");
			sysApp.createMenu("act_Custom_customList.action","navTab","customList","会员管理");
			sb.append(sysApp.toString());
			
			MenuUtil seat = new MenuUtil("前台管理");
			seat.createMenu("act_Bill_diningtableList.action","navTab","diningtableList","桌位管理");
			seat.createMenu("act_Bill_billList.action","navTab","billList","消费账单");
			seat.createMenu("act_Bill_historyBill.action","navTab","historyBill","历史账单");
			seat.createMenu("act_Bill_chufangList.action","navTab","chufangList","厨房传单");
			sb.append(seat.toString());
		}
		
		
		setAttribute("menu", sb.toString());
		return ActionUtil.Result.SUCCESS;
	}
	
	public String login() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String  role = request.getParameter("role");
		
		UserBean bean = userDao.login(username, password, role);
		
		
		if (bean != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute(ActionUtil.SESSION_USERNAME, bean.getUsername());
			session.setAttribute(ActionUtil.SESSION_ID, bean.getId());
			session.setAttribute(ActionUtil.SESSION_ROLE, bean.getRole());
			String role2 = (String)getSession(ActionUtil.SESSION_ROLE);
			setSession("roleNum", Role.valueOf(role2).ordinal());
			response
			.getWriter()
			.print(
					"<script language=javascript>alert('登录成功！');window.location.href='index.action';</script>");
		} else {
		
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者密码错误');window.location.href='login.jsp';</script>");

		}
		return null;
	}
	
	
	
	public void logout() throws IOException{
		getReq().getSession().invalidate();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
		.getWriter()
		.print(
				"<script language=javascript>alert('退出成功！');window.location.href='login.jsp';</script>");
		
	}
}
