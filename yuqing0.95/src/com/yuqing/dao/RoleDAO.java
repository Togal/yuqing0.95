package com.yuqing.dao;

import java.util.List;

import com.yuqing.model.Role;

public interface RoleDAO {

	public void save(Role transientInstance);
	public void delete(Role persistentInstance);
	public Role findById(Integer id);
	public List findByExample(Role instance);
	public List findByProperty(String propertyName, Object value);
	public List findByName(Object name);
	public List findAll();
	public List findNoAdmin();
	public Role findAdmin();
}
