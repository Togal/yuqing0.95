package com.yuqing.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yuqing.dao.ModuleDAO;
import com.yuqing.dao.PowerDAO;
import com.yuqing.dao.RoleDAO;
import com.yuqing.model.Module;
import com.yuqing.model.Power;
import com.yuqing.model.Role;
import com.yuqing.service.IModuleService;


public class ModuleService implements IModuleService{
	
	private ModuleDAO moduleDAO;
	private PowerDAO powerDAO;
	private RoleDAO roleDao;
	
	public RoleDAO getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDAO roleDao) {
		this.roleDao = roleDao;
	}

	public ModuleDAO getModuleDAO() {
		return moduleDAO;
	}

	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}
	
	public PowerDAO getPowerDAO() {
		return powerDAO;
	}

	public void setPowerDAO(PowerDAO powerDAO) {
		this.powerDAO = powerDAO;
	}

	public List findAll() {
		return moduleDAO.findAll();
	}

	@Override
	public List<Module> getByRoleId(Integer roleId) {
		List<Module> modules = moduleDAO.getByRoleId(roleId);
		List<Module> mainModule = new ArrayList();
		for(Module m:modules) {
			if(m.getModules().size()>0) {
				mainModule.add(m);
			}
		}
		return mainModule;
	}
	
	public void saveModule(Module module) {
		this.moduleDAO.save(module);
		Power power = new Power();
		Role role = new Role();
		role = this.roleDao.findAdmin();
		power.setModule(module);
		power.setRole(role);
		power.setAdd(1);
		power.setDel(1);
		power.setFind(1);
		power.setUpdate(1);
		this.powerDAO.save(power);
	}
	
	public List findModuleParentName() {
		return this.moduleDAO.findParentName();
	}
	
	public void delModule(Module module) {
		this.powerDAO.deleteByModule(module.getModuleId());
		this.moduleDAO.delete(module);
	}



	public Module findModuleById(Integer moduleId) {
		
		return this.moduleDAO.findById(moduleId);
	}
}
