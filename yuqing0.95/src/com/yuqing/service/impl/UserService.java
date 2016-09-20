package com.yuqing.service.impl;

import java.util.List;

import com.yuqing.dao.UserDAO;
import com.yuqing.model.User;
import com.yuqing.service.IUserService;

public class UserService implements IUserService{

	private UserDAO userDao;
	
	public User login(String name,String password) {
		List<User> users = userDao.findByName(name);
		if(users.size() == 0) return null;
		User u = users.get(0);
		if(u == null) {
			return null;
		}else {
			if(u.getPassword().equals(password)) {
				return u;
			}else {
				return null;
			}
		}
	}
	
	public int getTotalCount() {
		return userDao.getTotalCount();
	}
	
	public List<User> findAll() {
		return userDao.findAll();
	}
	
	

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public void update(User u) {
		this.userDao.update(u);
	}
	
	public void updateRoleById(int userId,int roleId){
		userDao.updateRoleById(userId, roleId);
	}

	public boolean isExist(String name) {
		return userDao.isExist(name);
	}

	
	
}
