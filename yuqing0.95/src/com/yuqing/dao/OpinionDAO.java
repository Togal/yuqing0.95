package com.yuqing.dao;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.yuqing.model.Opinion;
import com.yuqing.model.OpinionReplyCount;

public interface OpinionDAO {
	public List findByLevel(Object level);

	public Integer getCountByLevel(int level);

	public List findByLevel(int level, int start, int count);//分页
	

	public List findByPublishTime(Date catchTime,int start,int count);
	
	public List findLatest(int start,int count);
	
	public List findByLike(String keyWord,int classify,int start,int count);
	
	public Opinion findById(Integer id);

	public Integer getCountByLikeKeyWord(String key,int classify);

	public List finByTitle(String key, int classify, int start, int count);

	public Object getCountByTitle(String title,int classify);

	public List findByWebSite(int websiteCategory, int start, int count);

	public Object getCountByWebSiteCategory(int fromCategory);

	public int getCountByTimeAndInterest(Date start,Date end, String interest);

	public List<OpinionReplyCount> getLocationTop5ByArea(String area);

	public List<OpinionReplyCount> getHotTop5();

	public List<OpinionReplyCount> getLatestTop5();

	public JSONArray getLatestHotReplyCount();
}
