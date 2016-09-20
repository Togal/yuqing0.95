package com.yuqing.daoImpl;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yuqing.dao.OpinionDAO;
import com.yuqing.model.Opinion;
import com.yuqing.model.OpinionReplyCount;

public class OpinionDAOImpl extends BaseHibernateDAOImpl implements OpinionDAO{
	
	private static final Logger log = LoggerFactory.getLogger(OpinionDAOImpl.class);

	public static final String TITLE = "title";
	public static final String AUTHOR = "author";
	public static final String PUBLISH_TIME = "publishTime";
	public static final String KEYWORD = "keyword";
	public static final String CONTENT = "content";
	public static final String URL = "url";
	public static final String AREA = "area";
	public static final String LEVEL = "level";

	public void save(Opinion transientInstance) {
		log.debug("saving Opinion instance");
		try {
			getSessionFactory().getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Opinion persistentInstance) {
		log.debug("deleting Opinion instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Opinion findById(java.lang.Integer id) {
		log.debug("getting Opinion instance with id: " + id);
		try {
			Opinion instance = (Opinion) getSessionFactory().getCurrentSession().get(
					"com.yuqing.model.Opinion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Opinion instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Opinion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List findByAuthor(Object author) {
		return findByProperty(AUTHOR, author);
	}

	public List findByPublishTime(Object publishTime) {
		return findByProperty(PUBLISH_TIME, publishTime);
	}

	public List findByKeyword(Object keyword) {
		return findByProperty(KEYWORD, keyword);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findByArea(Object area) {
		return findByProperty(AREA, area);
	}

	public List findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	public List findAll() {
		log.debug("finding all Opinion instances");
		try {
			String queryString = "from Opinion";
			Query queryObject = getSessionFactory().getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Opinion merge(Opinion detachedInstance) {
		log.debug("merging Opinion instance");
		try {
			Opinion result = (Opinion) getSessionFactory().getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Opinion instance) {
		log.debug("attaching dirty Opinion instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Opinion instance) {
		log.debug("attaching clean Opinion instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public Integer getCountByLevel(int level) {
		Session currentSession = getSessionFactory().getCurrentSession();
		SQLQuery sql = currentSession.createSQLQuery("select count(*) from OPINION where LEVEL="+level);
		Object o = sql.uniqueResult();
		return new Integer(((BigInteger)o).intValue());
	}

	@Override
	public List findByLevel(int level, int start, int count) {
		Session currentSession = getSessionFactory().getCurrentSession();
		Query q = currentSession.createQuery("from Opinion as o where o.level="+level);
		q.setFirstResult(start);
		q.setMaxResults(count);
		return q.list();
	}

	@Override
	public List findByPublishTime(Date catchTime, int start, int count) {
		Session currentSession = getSessionFactory().getCurrentSession();
		Query q = currentSession.createQuery("from Opinion as o where o.catchTime="+catchTime);
		q.setFirstResult(start);
		q.setMaxResults(count);
		return q.list();
	}

	@Override
	public List findLatest(int start, int count) {
		Session currentSession = getSessionFactory().getCurrentSession();
		Query q = currentSession.createQuery("from Opinion as o order by o.catchTime");
		q.setFirstResult(start);
		q.setMaxResults(count);
		return q.list();
	}

	@Override
	public List findByLike(String keyWord, int classify, int start, int count) {
		Session currentSession = getSessionFactory().getCurrentSession();
		String sql = "from Opinion as o1 where o1.opinionId in(select o2.opinionId from Opinion as o2 where o2.keyword like '%"+keyWord+"%')";
		if(classify != 0) {
			sql += " and o1.website.category="+classify;
		}
		Query q = currentSession.createQuery(sql);
		q.setFirstResult(start);
		q.setMaxResults(count);
		return q.list();
	}

	@Override
	public Integer getCountByLikeKeyWord(String key,int classify) {
		Session currentSession = getSessionFactory().getCurrentSession();
		String sql = "select count(*) from Opinion as o where o.keyword like '%"+key+"%'";
		if(classify!=0) {
			sql += ("and o.website.category="+classify);
		}
//System.out.println("sql:"+sql);
		Query q = currentSession.createQuery(sql);
		Object o = q.uniqueResult();
		
		return new Integer(((Long)o).intValue());
	}

	@Override
	public List finByTitle(String key, int classify, int start, int count) {
		Session currentSession = getSessionFactory().getCurrentSession();
		String sql = "from Opinion as o where o.title='"+key+"'";
		if(classify!=0) {
			sql += " and o.website.category="+classify;
		}
		Query q = currentSession.createQuery(sql);
		q.setFirstResult(start);
		q.setMaxResults(count);
		return q.list();
	}

	@Override
	public Object getCountByTitle(String title,int classify) {
		Session currentSession = getSessionFactory().getCurrentSession();
		String sql = "select count(*) from Opinion as o where o.title='"+title+"'";
		if(classify!=0) {
			sql += " and o.website.category="+classify;
		}
		Query q = currentSession.createQuery(sql);
		Object o = q.uniqueResult();
		return new Integer(((Long)o).intValue());
	}

	@Override
	public List findByWebSite(int websiteCategory, int start, int count) {
		Session currentSession = getSessionFactory().getCurrentSession();
		Query q = currentSession.createQuery("from Opinion as o where o.website.category="+websiteCategory);
		q.setFirstResult(start);
		q.setMaxResults(count);
		return q.list();
	}

	@Override
	public Object getCountByWebSiteCategory(int fromCategory) {
		Session currentSession = getSessionFactory().getCurrentSession();
		Query s = currentSession.createQuery("select count(*) from Opinion as o where o.website.category="+fromCategory);
		Object o = s.uniqueResult();
		return new Integer(((Long)o).intValue());
	}

	@Override
	public int getCountByTimeAndInterest(Date start,Date end, String interest) {
		Session currentSession = getSessionFactory().getCurrentSession();
		Query q = currentSession.createQuery("select count(*) from Opinion as o where o.catchTime>=?" +
				" and o.catchTime<=? and (o.keyword like ? or ? like o.title)");
		q.setParameter(0, start);
		q.setParameter(1, end);
		q.setParameter(2, "%"+interest+"%");
		q.setParameter(3,"%"+ interest+"%");
		Object o = q.uniqueResult();
//System.out.println("count:"+o);
		return new Integer(((Long)o).intValue());
	}

	@Override
	public List<OpinionReplyCount> getLocationTop5ByArea(String area) {
		String[] areas = area.split("-");
		Session session = getSessionFactory().getCurrentSession();
		String sqlStr = "SELECT opinion_id,COUNT(targetid),title FROM opinion,reply WHERE opinion_id = targetid ";
		if(areas != null && areas.length>0) {
			sqlStr += " AND (";
			for(int i=0;i<areas.length;i++) {
				String a = areas[i];
				if(i==0) {
					sqlStr+="region like '%"+a+"%' ";
				}
				else {
					sqlStr+="or region like '%"+a+"%' ";
				}
			}
			sqlStr+=") ";
		}
		sqlStr+="GROUP BY targetid ORDER BY COUNT(targetid) DESC LIMIT 0,5";
//System.out.println(sqlStr);
		SQLQuery sql = session.createSQLQuery(sqlStr);
		List objs = sql.list();
		List<OpinionReplyCount> lists = new ArrayList<OpinionReplyCount>();
		for(int i=0;i<objs.size();i++) {
			OpinionReplyCount orc = new OpinionReplyCount();
			Opinion o = new Opinion();
			Object[] objss = (Object[])objs.get(i);
			orc.setReplyCount(new Integer(((BigInteger)objss[1]).intValue()));
			o.setOpinionId(new Integer((Integer)objss[0]));
			o.setTitle((String)objss[2]);
			orc.setOpinion(o);
			lists.add(orc);
		}
		return lists;
	}

	@Override
	public List<OpinionReplyCount> getHotTop5() {
		Session session = getSessionFactory().getCurrentSession();
		SQLQuery sql = session.createSQLQuery("SELECT opinion_id,COUNT(targetid),title FROM opinion,reply WHERE opinion_id = targetid GROUP BY targetid ORDER BY COUNT(targetid) DESC limit 0,5");
		List objs = sql.list();
		List<OpinionReplyCount> lists = new ArrayList<OpinionReplyCount>();
		for(int i=0;i<objs.size();i++) {
			OpinionReplyCount orc = new OpinionReplyCount();
			Opinion o = new Opinion();
			Object[] objss = (Object[])objs.get(i);
			orc.setReplyCount(new Integer(((BigInteger)objss[1]).intValue()));
			o.setOpinionId(new Integer((Integer)objss[0]));
			o.setTitle((String)objss[2]);
			orc.setOpinion(o);
			lists.add(orc);
		}
		return lists;
	}

	@Override
	public List<OpinionReplyCount> getLatestTop5() {
		Session session = getSessionFactory().getCurrentSession();
		SQLQuery sql = session.createSQLQuery("SELECT opinion_id,COUNT(targetid),title FROM opinion,reply WHERE opinion_id = targetid GROUP BY targetid ORDER BY publish_time DESC LIMIT 0,5;");
		List objs = sql.list();
		List<OpinionReplyCount> lists = new ArrayList<OpinionReplyCount>();
		for(int i=0;i<objs.size();i++) {
			OpinionReplyCount orc = new OpinionReplyCount();
			Opinion o = new Opinion();
			Object[] objss = (Object[])objs.get(i);
			orc.setReplyCount(new Integer(((BigInteger)objss[1]).intValue()));
			o.setOpinionId(new Integer((Integer)objss[0]));
			o.setTitle((String)objss[2]);
			orc.setOpinion(o);
			lists.add(orc);
		}
		return lists;
	}

	@Override
	public JSONArray getLatestHotReplyCount() {
		Session session = getSessionFactory().getCurrentSession();
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONArray jsonArray = new JSONArray();
		int dateCount = 5;
		int topCount = 4;
		String sql = "SELECT r.targetid,title FROM (SELECT targetid FROM reply WHERE DATE_SUB(CURRENT_DATE(),INTERVAL ? DAY) < `time` GROUP BY targetid ORDER BY COUNT(targetid) desc LIMIT ?) AS r,opinion WHERE r.targetid=opinion_id";
		SQLQuery q = session.createSQLQuery(sql);
		q.setParameter(0, dateCount);
		q.setParameter(1, topCount);
		List<Object[]> objs = q.list();
		for(Object[] os:objs) {
			calendar.setTime(new Date());
			calendar.add(Calendar.DATE, -1*dateCount);
			JSONObject jo = new JSONObject();
			JSONArray tempArray = new JSONArray();
			for(int i=0;i<dateCount;i++) {
				calendar.add(Calendar.DATE, 1);
				sql = "SELECT COUNT(targetid) FROM reply WHERE targetid=? AND DATE_FORMAT(`time`,'%Y-%m-%d')=?;";
				q = session.createSQLQuery(sql);
				q.setParameter(0, os[0]);
				q.setParameter(1, sdf.format(calendar.getTime()));
				Object rs = q.uniqueResult();
				tempArray.add(rs);
			}
			jo.put("data", tempArray);
			jo.put("name", os[1]);
			
			jsonArray.add(jo);
		}
		
		
		return jsonArray;
	}
	

}