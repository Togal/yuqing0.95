package com.yuqing.service;

import java.util.List;

import com.yuqing.model.User;

public interface IUserService {
	
	public User login(String name,String password);
	
	public int getTotalCount();
	
	public List<User> findAll();

	public void update(User u);
	
	public void updateRoleById(int userId,int roleId);

	public boolean isExist(String name);
}
