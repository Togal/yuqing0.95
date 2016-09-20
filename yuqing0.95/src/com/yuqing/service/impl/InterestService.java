package com.yuqing.service.impl;

import java.util.List;

import net.sf.json.JSONArray;

import com.yuqing.dao.InterestDAO;
import com.yuqing.model.Interest;
import com.yuqing.model.OpinionReplyCount;
import com.yuqing.model.User;
import com.yuqing.service.IInterestService;
import com.yuqing.tools.Digital;

public class InterestService implements IInterestService{

	private InterestDAO interestDao;

	public InterestDAO getInterestDao() {
		return interestDao;
	}

	public void setInterestDao(InterestDAO interestDao) {
		this.interestDao = interestDao;
	}

	public List<Interest> getInterestsByUserId(Integer userId) {
		List<Interest> interestList = interestDao.findByProperty("user.userId", userId);
		return interestList;
	}
	
	

//	public boolean update(int userId,User user,String interests) {
//		if(interestDao.isExit(userId)){
//		//	interestDao.update(userId,interests);
//		}else {
//		//	Interest i = new Interest(userId,user,interests);
//		//	interestDao.save(i);
//		}
//		return true;
//	}

	public boolean add(User user, String keyWord) {
		Interest i = new Interest(0,user,keyWord);
		interestDao.save(i);
		return true;
	}

	public void del(String interestId) {
		interestDao.delete(Digital.conversion(interestId, 0));
	}

	public List<OpinionReplyCount> getInterestTop5ByUserId(Integer userId) {
		return interestDao.getInterestTop5ByUserId(userId);
	}

	public JSONArray getInteresetReplyCount(Integer userId) {
		return interestDao.getInterestReplyCount(userId);
	}
}
