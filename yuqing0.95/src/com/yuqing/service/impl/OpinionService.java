package com.yuqing.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.json.JSONArray;

import com.yuqing.constant.Level;
import com.yuqing.dao.OpinionDAO;
import com.yuqing.model.Opinion;
import com.yuqing.model.OpinionReplyCount;
import com.yuqing.service.IOpinionService;

public class OpinionService implements IOpinionService {

	private OpinionDAO opinionDao;
	
	public List getOpinionByLevel(int level,int start,int count) {
		List opinions =  opinionDao.findByLevel(level,start,count);
//		for(int i=0;i<opinions.size();i++) {
//			Opinion o = (Opinion)opinions.get(i);
//			o.setTitle(Str.sub(o.getTitle(),30));
//			o.setContent(Str.sub(o.getContent(), 60));
//		}
		return opinions;
	}

	public OpinionDAO getOpinionDao() {
		return opinionDao;
	}

	public void setOpinionDao(OpinionDAO opinionDao) {
		this.opinionDao = opinionDao;
	}

	public int getOpinionCountByLevel(int level) {
		Object o = this.opinionDao.getCountByLevel(level);
		return ((Integer)o).intValue();
	}

	public Opinion getOpinionById(int opinionId) {
		Opinion o = this.opinionDao.findById(opinionId);
		if(o == null) {
			o = (Opinion) this.opinionDao.findLatest(1, 1).get(0);
		}
		return o;
	}

	/**
	 * 根据内容，查找类别，查找范围来查找舆情
	 * @param key 查找输入的内容
	 * @param classify 舆情的分类，如：博客
	 * @param searchMethod 查找方式，如模糊查找
	 * @return 查询到的舆情列表
	 */
	public List getOpinions(String key, int classify, int searchMethod,int start,int count) {
		List opinions = null;
		if(searchMethod == 1) {
			opinions = opinionDao.findByLike(key, classify, start, count);
		}else {
			opinions = opinionDao.finByTitle(key,classify,start,count);
		}
		return opinions;
	}

	public int getOpinionCountByLikeKeyWord(String key,int classify) {
		Object o = this.opinionDao.getCountByLikeKeyWord(key,classify);
		return ((Integer)o).intValue();
	}

	public int getCountByTitle(String title,int classify) {
		Object o = this.opinionDao.getCountByTitle(title,classify);
		return ((Integer)o).intValue();
	}

	public List getOpinions(int websiteId, int start, int count) {
		return opinionDao.findByWebSite(websiteId,start,count);
	}

	public int getCountByWebSiteCategory(int fromCategory) {
		Object o = this.opinionDao.getCountByWebSiteCategory(fromCategory);
		return ((Integer)o).intValue();
	}

	public String getCategoryCount() {
		String str = "["+opinionDao.getCountByWebSiteCategory(1);
		for(int i=2;i<7;i++) {
			str+=",";
			str+=this.opinionDao.getCountByWebSiteCategory(i);
		}
		str+="]";
		return str;
	}

	public String getLevelCount() {
		String str = "[";
		Set<Integer> keySet = Level.levelMap.keySet();
		Iterator<Integer> it = keySet.iterator();
		if(it.hasNext()) {str+=this.opinionDao.getCountByLevel(it.next());}
		while(it.hasNext()) {
			str+=(","+this.opinionDao.getCountByLevel(it.next()));
		}
		str+="]";
		return str;
	}
	
	public String getLevelName() {
		String str = "[";
		Set<Integer> keySet = Level.levelMap.keySet();
		Iterator<Integer> it = keySet.iterator();
		if(it.hasNext()) {str+=("'"+Level.levelMap.get(it.next())+"'");	}
		while(it.hasNext()) {
			str+=(",'"+Level.levelMap.get(it.next())+"'");
		}
		str+="]";
		return str;
	}


	public List<OpinionReplyCount> getLatestTop5() {
		return opinionDao.getLatestTop5();
	}

	public List<OpinionReplyCount> getHotTop5() {
		return opinionDao.getHotTop5();
	}

	public List<OpinionReplyCount> getLocationTop5ByArea(String area) {
		return opinionDao.getLocationTop5ByArea(area);
	}

	public JSONArray getLatestHotReplyCount() {
		return opinionDao.getLatestHotReplyCount();
	}
	
}
