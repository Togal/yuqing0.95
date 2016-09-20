package com.yuqing.dao;

import java.util.List;

import com.yuqing.model.MailBox;

public interface MailBoxDAO {
	
	public void save(MailBox transientInstance);
	public void delete(MailBox persistentInstance);
	public MailBox findById(Integer id);
	public List findByExample(MailBox instance);
	public List findByProperty(String propertyName, Object value);
	public List findAll();
	public boolean delete(Integer m);
}
