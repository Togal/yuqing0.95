package com.yuqing.dao;

import java.util.List;

import com.yuqing.model.Interest;
import com.yuqing.model.Reply;
import com.yuqing.model.ReplyCountAndTime;

public interface ReplyDAO {

	public void save(Reply transientInstance);
	public void delete(Reply persistentInstance);
	public Reply findById(java.lang.Integer id);
	public List findByExample(Reply instance);
	public List findByProperty(String propertyName, Object value);
	public List findAll();
	public List<Reply> getRepliesByOpinionId(int opinionId);
	public Integer getCountByOpinionIdAndRegion(int opinionId, String location);
	public List<ReplyCountAndTime> getTimeByInterest(Interest i);
	public List<ReplyCountAndTime> getCountAndTimeByOpinionId(int opinionId);
}
