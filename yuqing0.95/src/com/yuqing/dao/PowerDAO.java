package com.yuqing.dao;

import java.util.List;

import com.yuqing.model.Power;

public interface PowerDAO {
	
	public void save(Power transientInstance);
	public void delete(Power persistentInstance);
	public Power findById(java.lang.Integer id);
	public List findByExample(Power instance);
	public List findByProperty(String propertyName, Object value);
	public List findAll();
	
	public List findByRoleId(java.lang.Integer id);
	public void UpdatePower(Integer powerId,String op, Integer value);
	public void deleteByModule(Integer moduleId);
	
	public void addModuleRole(Power power);
	public void deleteModuleRole(Power power);
	
}
