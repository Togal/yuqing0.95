package com.yuqing.service;

import java.util.List;

import com.yuqing.dao.PowerDAO;
import com.yuqing.model.Power;

public interface IPowerService {
	
	public List findByRoleId(java.lang.Integer id);
	
	public void updatePower(Integer powerId, String op,Integer value);
	
	public void deleteByModule(Integer moduleId);
	
	public List findAll();
	
	public void addModuleRole(Power power);
	public void deleteModuleRole(Power power);
	
}
