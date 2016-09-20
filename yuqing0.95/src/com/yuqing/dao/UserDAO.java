package com.yuqing.dao;

import java.util.List;

import com.yuqing.model.User;

public interface UserDAO {

	public void save(User transientInstance);
	public void update(User u);
	public void delete(User persistentInstance);
	public User findById(java.lang.Integer id);
	public List findByExample(User instance);
	public List findByProperty(String propertyName, Object value);
	public List findByNick(Object nick);
	public List findByArea(Object area);
	public List findByPhone(Object phone);
	public List findAll();
	public int getTotalCount();
	public List findByName(Object name);
	public void updateRoleById(int userId,int roleId);
	public boolean isExist(String name);

}
