package com.yuqing.service.impl;

import java.util.List;

import com.yuqing.dao.ReplyDAO;
import com.yuqing.model.Interest;
import com.yuqing.model.Reply;
import com.yuqing.model.ReplyCountAndTime;
import com.yuqing.service.IReplyService;

public class ReplyService implements IReplyService{
	
	private ReplyDAO replyDao;

	public List findAll() {
		return replyDao.findAll();
	}

	public ReplyDAO getReplyDao() {
		return replyDao;
	}

	public void setReplyDao(ReplyDAO replyDao) {
		this.replyDao = replyDao;
	}

	public List<Reply> getRepliesByOpinionId(int opinionId) {
		return replyDao.getRepliesByOpinionId(opinionId);
	}

	public Integer getCountByOpinionIdAndRegion(int opinionId, String location) {
		return replyDao.getCountByOpinionIdAndRegion(opinionId,location);
	}

	public List<ReplyCountAndTime> getTimeByInterest(Interest i) {
	
		return replyDao.getTimeByInterest(i);
	}

	@Override
	public List<ReplyCountAndTime> getCountAndTimeByOpinionId(int opinionId) {
		
		return replyDao.getCountAndTimeByOpinionId(opinionId);
	}



	
}
