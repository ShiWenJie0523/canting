package com.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.Role;
import com.bean.UserBean;

public class UserDao extends HibernateDaoSupport {
	
	private UserDao(){
		
	}
	@SuppressWarnings("unchecked")
	public UserBean findUserByName(String username){
		List<UserBean> list = this.getHibernateTemplate().find("from UserBean where username=?",username);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public void addUser(UserBean user){
		this.getHibernateTemplate().save(user);
	}
	
	public void deleUser(int id){
		this.getHibernateTemplate().delete(findUserById(id));
	}
	
	public void updateUser(UserBean user){
		this.getHibernateTemplate().update(user);
	}
	
	public UserBean findUserById(int id){
		return this.getHibernateTemplate().get(UserBean.class, id);
	}
	
	
	public Long getUserCount(){
		return (Long)this.getHibernateTemplate().find("select count(*) from UserBean where role!=?",Role.getRole(0)).get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserBean> getUserList(final int pn,final int ps){
		return (List<UserBean>)this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createQuery("from UserBean where role!=? order by id desc")
							.setString(0, Role.getRole(0))
							.setFirstResult((pn-1)*ps)
							.setMaxResults(ps)
							.list();
			}
		});
	}
	
	public Long getUserCount(String role){
		return (Long)this.getHibernateTemplate().find("select count(*) from UserBean where role=?",role).get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserBean> getUserList(final String role,final int pn,final int ps){
		return (List<UserBean>)this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createQuery("from UserBean where role=? order by id desc")
							.setString(0, role)
							.setFirstResult((pn-1)*ps)
							.setMaxResults(ps)
							.list();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public UserBean login(String username,String password,String role){
		List<UserBean> list = this.getHibernateTemplate().find("from UserBean where username=? and userpwd=? and role=?",username,password,role);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
