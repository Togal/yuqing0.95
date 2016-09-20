package com.yuqing.service;

import java.util.List;

import com.yuqing.model.User;

public interface ILogService {


	public void addLoginLog(User u,String ip);

	public int getTotalCount(int userId);

	public List get(int userId,int start, int maxCount);
}
