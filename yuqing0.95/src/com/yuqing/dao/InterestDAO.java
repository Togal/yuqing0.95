package com.yuqing.dao;

import java.util.List;

import net.sf.json.JSONArray;

import com.yuqing.model.Interest;
import com.yuqing.model.OpinionReplyCount;

public interface InterestDAO {
	
	public Interest findById(Integer id);
	
	public List findByExample(Interest i);
	
	public List findByProperty(String propertyName, Object value);
	
	public boolean update(Interest i);

	public boolean isExit(Integer id);

	public void save(Interest i);
	
	public void delete(Interest i);

	public void delete(int ids);

	public List<OpinionReplyCount> getInterestTop5ByUserId(Integer userId);

	public JSONArray getInterestReplyCount(Integer userId);
}	
