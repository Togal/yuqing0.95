package com.yuqing.service.impl;

import java.util.Date;
import java.util.List;

import com.yuqing.dao.LogDAO;
import com.yuqing.model.Log;
import com.yuqing.model.User;
import com.yuqing.service.ILogService;

public class LogService implements ILogService{

	private LogDAO logDao;

	public LogDAO getLogDao() {
		return logDao;
	}

	public void setLogDao(LogDAO logDao) {
		this.logDao = logDao;
	}

	public void addLoginLog(User u,String ip) {
		Log log = new Log(u, "登录IP:"+ip, new Date());
		logDao.save(log);
	}

	public int getTotalCount(int userId) {
		int totalCount = logDao.getTotalCount(userId);
		return totalCount;
	}

	public List get(int userId,int start, int maxCount) {
		String sql = "from Log as l where l.user.userId="+userId + " ORDER BY l.time DESC";
		return logDao.listByCustom(sql, start, maxCount);
	}
}
