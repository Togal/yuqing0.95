package com.yuqing.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.yuqing.dao.BaseHibernateDAO;


public class BaseHibernateDAOImpl implements BaseHibernateDAO{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List listByCustom(String sql, int start, int maxCount) {
		Session s = getSessionFactory().getCurrentSession();
		Query q = s.createQuery(sql);
		if(start >=0 ){
			q.setFirstResult(start);
			q.setMaxResults(maxCount);
		}
		return q.list();
	}
	
	
	
}