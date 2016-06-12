package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.bean.Role;
import com.bean.UserBean;
import com.dao.CommonDao;
import com.dao.UserDao;
import com.util.ActionUtil;
import com.util.ClassBeanFactory;
import com.util.Utils;

public class UserAction extends BaseAction {

	
	private static final long serialVersionUID = 1L;
	private UserDao userDao = (UserDao)ClassBeanFactory.getBean("userDao");
	private CommonDao commDao = (CommonDao)ClassBeanFactory.getBean("commDao");
	
	public String userList(){
		long count = commDao.getObjectCount(UserBean.class);
		List<UserBean> list = commDao.getObjectList2(UserBean.class, getPageNum(), getNumPerPage());
		setAttribute("list", list);
		setAttribute("totalCount", count);
		return ActionUtil.Result.SUCCESS;
	}
	
	public String addUser() throws IOException{
		PrintWriter out = this.getWriter();
		UserBean uu = userDao.findUserByName(getParameter("username"));
		if(uu!=null){
			out.write(Utils.outPutMsg("300", "该用户已存在", "", "", false, ""));
			out.flush();
			return null;
		}
		int r = 1;
		UserBean user = new UserBean();
		user.setUsername(getParameter("username"));
		user.setUserpwd(getParameter("userpwd"));
		user.setStatus("true");
		user.setRole(Role.getRole(r));
		/**额外数量**/
		user.setRealName(getParameter("realName"));
		commDao.addObject(user);
		out.write(Utils.outPutMsg("200", "添加成功", "userList", "", true, "act_User_userList.action"));
		out.flush();
		return null;
	}
	
	public String changePwd() throws Exception{
		PrintWriter out = getWriter();
		String username = (String)getSession(ActionUtil.SESSION_USERNAME);
		
		String oldpwd = getParameter("oldpwd");
		String newspwd = getParameter("newspwd");
		UserBean bean = userDao.findUserByName(username);
		
		if(!bean.getUserpwd().equals(oldpwd)){
			throw new Exception("旧密码错误");
		}
		bean.setUserpwd(newspwd);
		commDao.updateObject(bean);
		
		out.write(Utils.outPutMsg("200", "修改成功", "pwd", "", true, "to_User_pwd.action"));
		out.flush();
		return null;
	}
	
	public String deleUser() throws IOException{
		PrintWriter out = getWriter();
		int id = Integer.parseInt(getParameter("id"));
		commDao.deleObject(UserBean.class, id);
		out.write(Utils.outPutMsg("200", "删除成功", "", "", false, ""));
		out.flush();
		return null;
	}
	
	public String findUser(){
		int id = Integer.parseInt(getParameter("id"));
		UserBean user = (UserBean)commDao.findObjectById(UserBean.class, id);
		setAttribute("bean", user);
		return ActionUtil.Result.SUCCESS;
	}
	
	public String updateUser() throws IOException{
		PrintWriter out = getWriter();
		int id = Integer.parseInt(getParameter("id"));
		UserBean user = (UserBean)commDao.findObjectById(UserBean.class, id);
		user.setRealName(getParameter("realName"));
		commDao.updateObject(user);
		out.write(Utils.outPutMsg("200", "修改成功", "userList", "", true, "act_User_userList.action"));
		out.flush();
		return null;
	}
	
	
}
