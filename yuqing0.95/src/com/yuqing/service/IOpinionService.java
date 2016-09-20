package com.yuqing.service;


import java.util.List;

import net.sf.json.JSONArray;

import com.yuqing.model.Opinion;
import com.yuqing.model.OpinionReplyCount;

public interface IOpinionService {

	public List getOpinionByLevel(int level,int start,int count);

	public int getOpinionCountByLevel(int level);

	public Opinion getOpinionById(int opinionId);

	/**
	 * 根据内容，查找类别，查找范围来查找舆情
	 * @param key 查找输入的内容
	 * @param classify 舆情的分类，如：博客
	 * @param searchMethod 查找方式，如模糊查找
	 * @return 查询到的舆情列表
	 */
	public List getOpinions(String key, int classify, int searchMethod,int start,int count);

	public int getOpinionCountByLikeKeyWord(String key,int classify);

	public int getCountByTitle(String title,int classify);

	public List getOpinions(int websiteId, int start, int count);

	public int getCountByWebSiteCategory(int fromCategory);

	public String getCategoryCount();

	public String getLevelCount();
	
	public String getLevelName();


	public List<OpinionReplyCount> getLatestTop5();

	public List<OpinionReplyCount> getHotTop5();

	public List<OpinionReplyCount> getLocationTop5ByArea(String area);

	public JSONArray getLatestHotReplyCount();
	
}
