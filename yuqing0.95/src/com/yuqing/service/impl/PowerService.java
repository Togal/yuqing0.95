package com.yuqing.service.impl;

import java.util.List;

import com.yuqing.dao.PowerDAO;
import com.yuqing.model.Power;
import com.yuqing.service.IPowerService;

public class PowerService implements IPowerService{
		
	private PowerDAO powerDAO;

	public PowerDAO getPowerDAO() {
		return powerDAO;
	}

	public void setPowerDAO(PowerDAO powerDAO) {
		this.powerDAO = powerDAO;
	}
	
	public List findByRoleId(java.lang.Integer id) {
		return powerDAO.findByRoleId(id);
	}
	
	public void updatePower(Integer powerId, String op,Integer value){
		powerDAO.UpdatePower(powerId, op,value);
	}

	public void deleteByModule(Integer moduleId) {
			this.powerDAO.deleteByModule(moduleId);
	}

	@Override
	public List findAll() {
		return this.powerDAO.findAll();
	}
	
	
	public void addModuleRole(Power power) {
		this.powerDAO.save(power);
	}

	public void deleteModuleRole(Power power) {
		System.out.println("module" + power.getModule().getName());
		this.powerDAO.deleteModuleRole(power);
	}
}
