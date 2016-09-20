package com.yuqing.dao;

import java.util.List;

import com.yuqing.model.Log;

public interface LogDAO extends BaseHibernateDAO{
	
	public void save(Log transientInstance);
	public void delete(Log persistentInstance);
	public Log findById(java.lang.Integer id);
	public List findByExample(Log instance);
	public List findByProperty(String propertyName, Object value);
	public List findAll();
	public Integer getTotalCount(int userId);
}
