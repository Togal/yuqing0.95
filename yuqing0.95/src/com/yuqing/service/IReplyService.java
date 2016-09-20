package com.yuqing.service;

import java.util.List;

import com.yuqing.model.Interest;
import com.yuqing.model.Reply;
import com.yuqing.model.ReplyCountAndTime;

public interface IReplyService {

	public List findAll();

	public List<Reply> getRepliesByOpinionId(int opinionId);

	public Integer getCountByOpinionIdAndRegion(int opinionId, String location);
	
	public List<ReplyCountAndTime> getTimeByInterest(Interest i);

	public List<ReplyCountAndTime> getCountAndTimeByOpinionId(int opinionId);



	
}
