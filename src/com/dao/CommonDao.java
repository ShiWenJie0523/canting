package com.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CommonDao extends HibernateDaoSupport {
	public void addObject(Object obj){
		this.getHibernateTemplate().save(obj);
	}
	
	public <T> void deleObject(Class<T> clazz,int id){
		this.getHibernateTemplate().delete(findObjectById(clazz,id));
	}
	
	public void updateObject(Object obj){
		this.getHibernateTemplate().update(obj);
	}
	
	public <T> T findObjectById(Class<T> clazz,int id){
		return this.getHibernateTemplate().get(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getList(Class<T> clazz){
		return this.getHibernateTemplate().find("from "+clazz.getSimpleName()+" order by id desc");
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getList(Class<T> clazz,String where,Object... values){
		return this.getHibernateTemplate().find("from "+clazz.getSimpleName()+where+" order by id desc",values);
	}
	
	public <T> Long getObjectCount(Class<T> clazz){
		return (Long)this.getHibernateTemplate().find("select count(*) from "+clazz.getSimpleName()).get(0);
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getObjectList(final Class<T> clazz,final int pn,final int ps){
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createQuery("from "+clazz.getSimpleName()+"   order by id desc")
							.setFirstResult((pn-1)*ps)
							.setMaxResults(ps)
							.list();
			}
		});
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getObjectList2(final Class<T> clazz,final int pn,final int ps){
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createQuery("from "+clazz.getSimpleName()+"  where role!='ROLE_ROOT' order by id desc")
							.setFirstResult((pn-1)*ps)
							.setMaxResults(ps)
							.list();
			}
		});
	}
	
	
	public <T> Long getObjectCount(final Class<T> clazz,String where,Object... values){

		
		return (Long)this.getHibernateTemplate().find("select count(*) from "+clazz.getSimpleName()+where,values).get(0);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getObjectList(final Class<T> clazz,final String where,final Object[] objArr,final int pn,final int ps){
		return this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("from "+clazz.getSimpleName()+where+" order by id desc");
				for(int i=0;i<objArr.length;i++){
					Object obj = objArr[i];
					if(obj instanceof Integer){
						query.setInteger(i, (Integer)obj);
					}
					if(obj instanceof String){
						query.setString(i, (String)obj);
					}
					if(obj instanceof Double){
						query.setDouble(i, (Double)obj);
					}
					if(obj instanceof Date){
						query.setDate(i, (Date)obj);
					}
					if(obj instanceof Enum){
						query.setInteger(i, ((Enum)obj).ordinal());
					}
				}
				query.setFirstResult((pn-1)*ps);
				query.setMaxResults(ps);
				return query.list();
			}
		});
	}
	
	/**
	 * 执行sql语句
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getSqlList(final String sql){
		return (List<Object>)this.getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				return session.createSQLQuery(sql).list();
			}
		});
	}
}
